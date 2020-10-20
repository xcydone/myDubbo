package com.crossyf.dubbo.common.zookeeper.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crossyf.dubbo.common.zookeeper.event.Event;
import com.crossyf.dubbo.common.zookeeper.event.EventListener;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.*;

/**
 * Zookeeper事件管理类
 * @author caif
 * @date 2020/4/3 11:09 上午
 */
public class EventManager {

	private Logger log = LoggerFactory.getLogger(EventManager.class);

	private int cacheExpiredEvent;

	// 节点路径
	private String path = "/event";

	// 线程安全的阻塞队列，每个节点存放的数据都是List<Event> 从head取元素，从tail添加元素
	private LinkedBlockingQueue<List<Event>> events = new LinkedBlockingQueue<List<Event>>();

	// zk连接 没有用注解是因为当一个变量，config启动的时候，赋值了
	private ZookeeperManager zookeeper;

	// 定时器？？
	protected ScheduledExecutorService scheduler;

	// 有序map  key-事件 value-事件的监听
	private Map<Class<? extends Event>, List<com.crossyf.dubbo.common.zookeeper.event.EventListener<Event>>> listeners = new LinkedHashMap<Class<? extends Event>, List<com.crossyf.dubbo.common.zookeeper.event.EventListener<Event>>>();

	@PostConstruct
	public void init() {
		log.info("===========EventManager init==========");
		scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r, "EventScheduler");
				t.setDaemon(false);
				return t;
			}
		});

		scheduler.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				List<Event> es = null;
				try {
					// peek() 返回队队顶元素，但顶元素不弹出。队列为空时返回null
					// 获取队顶元素，删除对应的节点之后，从队列中取出，直至队列中的节点全部被处理完
					while (accept(es = events.peek())) {
						for (Event e : es) {
							getZookeeper().getCurator().delete().forPath(path + "/" + e.getId());
							events.take();
							break;
						}
					}
				} catch (Exception ex) {
					log.error("Unable to clean event " + es, ex);
				}
			}

			// 判断是否超时，大于设置的超时时间就删除节点操作
			protected boolean accept(List<Event> e) {
				if (e == null)
					return false;
				if (e.size() == 0)
					return false;
				return System.currentTimeMillis() - e.get(0).getTimestamp() > cacheExpiredEvent;
			}

		}, 0, 1000L, TimeUnit.MILLISECONDS);

		PathChildrenCache watcher = new PathChildrenCache(getZookeeper().getCurator(), path, true);
		watcher.getListenable().addListener(new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) {
				log.info("Receive zookeeper event: " + event);
				// 只监听节点新增event
				if (event.getType() != PathChildrenCacheEvent.Type.CHILD_ADDED)
					return;

				List<Event> es = null;
				try {
					// 获取节点中存放的数据，events，就是创建节点时写入的事件events
					es = JSON.parseArray(new String(event.getData().getData(), "utf-8"), Event.class);
					for (Event e : es)
						internalProcess(e);
				} catch (Exception ex) {
					log.error("Unable to handle event: " + es, ex);
				}
			}

		});

		try {
			// 启动监听，模式是BUILD_INITIAL_CACHE
			watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
		} catch (Exception e) {
			log.error("Unable to start watcher", e);
		}
	}

	public void process(Event event) throws Exception {
		event.setId(UUID.randomUUID().toString());
		event.setTimestamp(System.currentTimeMillis());
		internalProcess(event);
	}

	// 执行自定义的监听动作
	protected void internalProcess(Event event) throws Exception {
		// 获取到这一类的event的listener组，逐个执行
		List<com.crossyf.dubbo.common.zookeeper.event.EventListener<Event>> ls = listeners.get(event.getClass());
		if (ls != null)
			for (com.crossyf.dubbo.common.zookeeper.event.EventListener<Event> listener : ls) {
				listener.execute(event);
			}

	}

	// 调用入口
	public void dispatcher(Event... events) throws Exception {
		// event加id和时间标签
		String id = UUID.randomUUID().toString();
		for (Event event : events) {
			event.setId(id);
			event.setTimestamp(System.currentTimeMillis());
		}
		// 创建临时节点，并带上初始内容，byte[]类型的这一批event放入节点中，id是随机uuid，其实也就是新增了节点，会被捕捉到
		getZookeeper().getCurator().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path + "/" + id,
				JSONObject.toJSONString(Arrays.asList(events), SerializerFeature.WriteClassName).getBytes("utf-8"));

		// 阻塞队列中放入一批次的event，这一批次的event，id都是用的同一个，也都是放在了zk上的同一个同名的节点上
		this.events.put(Arrays.asList(events));
	}

	public void addListener(com.crossyf.dubbo.common.zookeeper.event.EventListener<? extends Event> listener) {
		List<com.crossyf.dubbo.common.zookeeper.event.EventListener<Event>> ls = this.listeners.get(listener.type());
		if (ls == null)
			this.listeners.put(listener.type(), ls = new ArrayList<com.crossyf.dubbo.common.zookeeper.event.EventListener<Event>>());
		ls.add((EventListener<Event>) listener);
	}

	@PreDestroy
	public void destroy() {
		if (this.scheduler != null)
			this.scheduler.shutdown();

		this.listeners.clear();
	}

	public void setCacheExpiredEvent(int cacheExpiredEvent) {
		this.cacheExpiredEvent = cacheExpiredEvent;
	}

	public ZookeeperManager getZookeeper() {
		return zookeeper;
	}

	public void setZookeeper(ZookeeperManager zookeeper) {
		this.zookeeper = zookeeper;
	}

}