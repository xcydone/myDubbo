package com.crossyf.dubbo.tool.event;

/**
 * Zookeeper事件监听类
 * @author zuos
 * @date 2020/4/3 11:09 上午
 */
public interface EventListener<E extends Event> {

	public Class<E> type();

	public void execute(E event) throws Exception;

}