package com.crossyf.dubbo.common.zookeeper.manager;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Zookeeper管理类
 * @author caif
 * @date 2020/4/3 11:09 上午
 */
public class ZookeeperManager {

	private Logger log = LoggerFactory.getLogger(ZookeeperManager.class);

	private String namespace;
	private String hosts;
	// 认证信息
	private String digest;

	public CuratorFramework curator;

	@PostConstruct
	public void init() throws Exception {
		// 建立zk客户端和服务器之间的连接
		CuratorFrameworkFactory.Builder build = CuratorFrameworkFactory.builder().connectString(this.getHosts()).namespace(this.getNamespace())
				.retryPolicy(new RetryUntilElapsed(100, 10));

		// 如果存在认证消息就认证
		if (this.getDigest() != null) {
			build.authorization("digest", this.getDigest().getBytes("utf-8"));
		}

		curator = build.build();

		// 启动客户端
		curator.start();
		log.info("zookeeper客户端启动");
	}

	@PreDestroy
	public void destroy() {
		try {
			Thread.sleep(1000L);
		} catch (Exception e) {

		} // TODO 等待释放资源，暂时没有适合的方法可以安全释放zk
		curator.close();
		log.info("zookeeper客户端停止");
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getHosts() {
		return hosts;
	}

	public void setHosts(String hosts) {
		this.hosts = hosts;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public CuratorFramework getCurator() {
		return curator;
	}

	public void setCurator(CuratorFramework curator) {
		this.curator = curator;
	}

}