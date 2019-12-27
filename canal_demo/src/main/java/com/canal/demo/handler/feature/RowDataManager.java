package com.canal.demo.handler.feature;

import com.canal.demo.handler.feature.model.RowEvent;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class RowDataManager {
    /**
     * 消费队列
     */
    private static final LinkedBlockingDeque<RowEvent> ROW_DATAS = new LinkedBlockingDeque<>();

    public static RowEvent poll() {
        return ROW_DATAS.poll();
    }

    public static RowEvent poll(long timeOut, TimeUnit timeUnit) throws InterruptedException {
        return ROW_DATAS.poll(timeOut, timeUnit);
    }

    public static boolean offer(RowEvent row) {
        return ROW_DATAS.offer(row);
    }

    public static boolean offer(RowEvent row, long timeout, TimeUnit timeUnit) throws InterruptedException {
        return ROW_DATAS.offer(row, timeout, timeUnit);
    }
}
