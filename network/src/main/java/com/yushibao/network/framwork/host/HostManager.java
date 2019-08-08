package com.yushibao.network.framwork.host;

import android.support.annotation.NonNull;

import com.yushibao.network.api.HostEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 域名
 *
 * @author Zenfer
 * @date 2019/6/24 16:19
 */
public class HostManager {

    /**
     * 添加 Host
     *
     * @param hostKey {@link HostEnum}
     * @param host    域名
     */
    public void addHost(@HostEnum String hostKey, @NonNull String host) {
        HOST_MAP.put(hostKey, host);
    }

    /**
     * 获取 Host
     *
     * @param hostKey {@link HostEnum}
     * @return 域名
     */
    public String getHost(@HostEnum String hostKey) {
        return HOST_MAP.get(hostKey);
    }


    private Map<String, String> HOST_MAP = new ConcurrentHashMap<>(5);

    private HostManager() {
    }

    public static HostManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final HostManager INSTANCE = new HostManager();
    }
}
