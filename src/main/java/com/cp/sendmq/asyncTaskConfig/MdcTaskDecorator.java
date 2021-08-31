package com.cp.sendmq.asyncTaskConfig;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/31
 */
public class MdcTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            try {
                // Right now: @Async thread context !
                // (Restore the Web thread context's MDC data)
                MDC.setContextMap(contextMap);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
