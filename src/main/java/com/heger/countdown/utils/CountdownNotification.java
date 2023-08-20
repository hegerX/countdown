package com.heger.countdown.utils;

import com.intellij.notification.*;

public class CountdownNotification implements Notice {
    @Override
    public void notice(String time) {
        NotificationGroup notificationGroup1 = new NotificationGroup("剩余时间：", NotificationDisplayType.STICKY_BALLOON, true);
        Notification notification1 = notificationGroup1.createNotification(time + "分钟", NotificationType.ERROR);
        Notifications.Bus.notify(notification1);
    }

    @Override
    public void notice(String time, String message) {

    }
}
