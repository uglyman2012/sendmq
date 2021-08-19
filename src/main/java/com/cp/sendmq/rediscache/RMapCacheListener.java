package com.cp.sendmq.rediscache;

import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.api.map.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/19
 */
@Component
public class RMapCacheListener implements ApplicationRunner, Ordered {
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        listener();
    }


    @Override
    public int getOrder() {
        return 1;
    }

    private void listener() {
        RMapCache<String, Integer> rMapCache = redissonClient.getMapCache("catalog_test_id");
        rMapCache.addListener(new EntryCreatedListener<String, Object>() {
            @Override
            public void onCreated(EntryEvent<String, Object> event) {
                System.out.println("创建key:" + event.getKey() + ",value:" + event.getValue().toString());
            }
        });
        rMapCache.addListener(new EntryExpiredListener<String, Object>() {
            @Override
            public void onExpired(EntryEvent<String, Object> event) {
                System.out.println("过期移除key:" + event.getKey() + ",value:" + event.getValue().toString());
            }
        });
        rMapCache.addListener(new EntryRemovedListener<String, Object>() {
            @Override
            public void onRemoved(EntryEvent<String, Object> event) {
                System.out.println("主动删除ey:" + event.getKey() + ",value:" + event.getValue().toString());
            }
        });

        rMapCache.addListener(new EntryUpdatedListener<String, Object>() {
            @Override
            public void onUpdated(EntryEvent<String, Object> event) {
                System.out.println("主动修改ey:" + event.getKey() + ",value:" + event.getValue().toString());
            }
        });
    }
}
