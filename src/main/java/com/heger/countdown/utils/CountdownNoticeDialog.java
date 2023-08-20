package com.heger.countdown.utils;

import com.heger.countdown.ui.NoticeJDialog;

import java.util.concurrent.CompletableFuture;

public class CountdownNoticeDialog implements Notice {
    @Override
    public void notice(String time, String title) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 在这里执行异步任务
            NoticeJDialog dialog = NoticeJDialog.getInstance();
            dialog.setMsg(time, title);
            return "异步任务的结果";
        });
    }
}
