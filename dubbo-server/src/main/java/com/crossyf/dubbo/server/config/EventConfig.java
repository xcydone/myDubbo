package com.crossyf.dubbo.server.config;

import com.crossyf.dubbo.common.zookeeper.event.EventListener;
import com.crossyf.dubbo.common.zookeeper.manager.EventManager;
import com.crossyf.dubbo.common.zookeeper.manager.ZookeeperManager;
import com.crossyf.dubbo.second.event.RuleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "zookeeper")
@Configuration
@Slf4j
public class EventConfig {
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
        log.info("建立zk连接成功！");
        return zookeeperManager;
    }

    @Bean
    public EventManager eventManager(){
        EventManager eventManager =  new EventManager();
        eventManager.setZookeeper(zookeeperManager());
        eventManager.setCacheExpiredEvent(cacheExpiredEvent);

        // 设置事件的listener事件
        eventManager.addListener(new EventListener<RuleEvent>() {
            @Override
            public Class<RuleEvent> type() {
                return RuleEvent.class;
            }

            @Override
            public void execute(RuleEvent event) throws Exception {
                if(event.getAdtId() == 9902){
                    log.info("验证通过，adtId是9902");
                }
            }
        });

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
