package com.ayshriv.memovault_api.service;

import com.ayshriv.memovault_api.common.DesireStatus;
import com.ayshriv.memovault_api.entities.AppNotification;

public interface AppNotificationService {

    DesireStatus getCount(String username);

    DesireStatus editNotification(String username, AppNotification notification);

    DesireStatus deleteNotification(String username, long l);

    DesireStatus setReadStatus(String username, long l, boolean b);

    DesireStatus getAllNotification(String username);

    DesireStatus addNotification(String username, AppNotification notification);

    DesireStatus viewNotification(String username, long l);
}
