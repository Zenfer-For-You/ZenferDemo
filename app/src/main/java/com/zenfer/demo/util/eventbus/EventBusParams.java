package com.zenfer.demo.util.eventbus;

/**
 * event bus 数据传输实体
 *
 * @author Zenfer
 * @date 2018/12/18 21:11
 **/
public class EventBusParams {
    /**
     * EVENT BUS KEY
     */
    public String key;
    /**
     * EVENT BUS VALUE
     */
    public Object object;

    public EventBusParams(String key, Object object) {
        this.key = key;
        this.object = object;
    }
}
