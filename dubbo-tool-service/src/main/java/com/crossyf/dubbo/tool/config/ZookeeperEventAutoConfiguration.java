package com.crossyf.dubbo.tool.config;

import com.crossyf.dubbo.tool.manager.EventManager;
import com.crossyf.dubbo.tool.manager.ZookeeperManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * zookeeper-event-starter自动配置类
 * </p>
 *
 * @author zuos
 * @since 2020-04-03
 */
@ConditionalOnProperty(name = "zookeeper.event", havingValue = "true")
@ConfigurationProperties(prefix = "zookeeper")
@Configuration
@Slf4j
public class ZookeeperEventAutoConfiguration {

    private String namespace;
    private String hosts;
    private String digest;
    private int cacheExpiredEvent;

    @Bean
    public ZookeeperManager zookeeperManager(){
        ZookeeperManager zookeeperManager = new ZookeeperManager();
        zookeeperManager.setNamespace(namespace);
        zookeeperManager.setHosts(hosts);
        zookeeperManager.setDigest(digest);
        log.info("zk实例化成功！");
        return zookeeperManager;
    }

    @Bean
    public EventManager eventManager(){
        EventManager eventManager =  new EventManager();
        eventManager.setZookeeper(zookeeperManager());
        eventManager.setCacheExpiredEvent(cacheExpiredEvent);
        return eventManager;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setCacheExpiredEvent(int cacheExpiredEvent) {
        this.cacheExpiredEvent = cacheExpiredEvent;
    }
}
