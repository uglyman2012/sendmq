package com.cp.sendmq.factoryPatternDemo;

public interface ReceiveHandle {
    /**
     * 获取处理器名称
     *
     * @return
     */
    String getName();

    /**
     * 业务处理
     *
     * @param data 业务数据
     */
    void execute(String data);
}
