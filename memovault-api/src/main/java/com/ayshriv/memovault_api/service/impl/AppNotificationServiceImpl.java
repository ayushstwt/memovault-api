package com.ayshriv.memovault_api.service.impl;

import com.ayshriv.memovault_api.common.DesireStatus;
import com.ayshriv.memovault_api.entities.AppNotification;
import com.ayshriv.memovault_api.repository.AppNotificationRepository;
import com.ayshriv.memovault_api.service.AppNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppNotificationServiceImpl implements AppNotificationService {

    Logger LOGGER = LoggerFactory.getLogger(AppNotificationServiceImpl.class);

    @Autowired
    private AppNotificationRepository appNotificationRepository;


    @Override
    public DesireStatus getCount(String username) {
        return null;
    }

    @Override
    public DesireStatus editNotification(String username, AppNotification notification) {
        return null;
    }

    @Override
    public DesireStatus deleteNotification(String username, long l) {
        return null;
    }

    @Override
    public DesireStatus setReadStatus(String username, long l, boolean b) {
        return null;
    }

    @Override
    public DesireStatus getAllNotification(String username) {
        return null;
    }

    @Override
    public DesireStatus addNotification(String username, AppNotification notification) {
        return null;
    }

    @Override
    public DesireStatus viewNotification(String username, long l) {
        return null;
    }
}
