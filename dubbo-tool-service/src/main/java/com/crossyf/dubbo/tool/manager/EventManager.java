package com.crossyf.dubbo.tool.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crossyf.dubbo.tool.event.Event;
import com.crossyf.dubbo.tool.event.EventListener;
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
 * @author zuos
 * @date 2020/4/3 11:09 上午
 */
public class EventManager {

	private Logger log = LoggerFactory.getLogger(EventManager.class);

	private int cacheExpiredEvent;

	private String path = "/event";
	private LinkedBlockingQueue<List<Event>> events = new LinkedBlockingQueue<List<Event>>();

	private ZookeeperManager zookeeper;
	protected ScheduledExecutorService scheduler;

	private Map<Class<? extends Event>, List<com.crossyf.dubbo.tool.event.EventListener<Event>>> listeners = new LinkedHashMap<Class<? extends Event>, List<com.crossyf.dubbo.tool.event.EventListener<Event>>>();

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

				if (event.getType() != PathChildrenCacheEvent.Type.CHILD_ADDED)
					return;

				List<Event> es = null;

				try {

					es = JSON.parseArray(new String(event.getData().getData(), "utf-8"), Event.class);

					for (Event e : es)
						internalProcess(e);

				} catch (Exception ex) {
					log.error("Unable to handle event: " + es, ex);
				}
			}

		});

		try {
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

	protected void internalProcess(Event event) throws Exception {

		List<com.crossyf.dubbo.tool.event.EventListener<Event>> ls = listeners.get(event.getClass());

		if (ls != null)
			for (com.crossyf.dubbo.tool.event.EventListener<Event> listener : ls) {
				listener.execute(event);
			}

	}

	public void dispatcher(Event... events) throws Exception {
		String id = UUID.randomUUID().toString();
		for (Event event : events) {
			event.setId(id);
			event.setTimestamp(System.currentTimeMillis());
		}
		getZookeeper().getCurator().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path + "/" + id,
				JSONObject.toJSONString(Arrays.asList(events), SerializerFeature.WriteClassName).getBytes("utf-8"));
		this.events.put(Arrays.asList(events));
	}

	public void addListener(com.crossyf.dubbo.tool.event.EventListener<? extends Event> listener) {
		List<com.crossyf.dubbo.tool.event.EventListener<Event>> ls = this.listeners.get(listener.type());
		if (ls == null)
			this.listeners.put(listener.type(), ls = new ArrayList<com.crossyf.dubbo.tool.event.EventListener<Event>>());
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