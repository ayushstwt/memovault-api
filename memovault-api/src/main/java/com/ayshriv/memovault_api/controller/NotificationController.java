package com.ayshriv.memovault_api.controller;

import com.ayshriv.memovault_api.common.DesireStatus;
import com.ayshriv.memovault_api.common.Resources;
import com.ayshriv.memovault_api.entities.AppNotification;
import com.ayshriv.memovault_api.service.AppNotificationService;
import com.ayshriv.memovault_api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import static com.ayshriv.memovault_api.controller.JournalEntryController.LOGGER;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AppNotificationService appNotificationService;

    @PostMapping("/add/{username}")
    private MappingJacksonValue addNotification(@PathVariable String username, @RequestBody AppNotification notification) {
        LOGGER.info("NotificationController >> addNotification called.");
        DesireStatus status = appNotificationService.addNotification(username, notification);
        String[] properties = { "statusType", "text", "notification" };
        return Resources.formatedResponse(status, properties);
    }

    @GetMapping("/getAll/{username}")
    private MappingJacksonValue getAllNotification(@PathVariable String username) {
        LOGGER.info("NotificationController >> getAllNotification called.");
        DesireStatus status = appNotificationService.getAllNotification(username);
        String[] properties = { "statusType", "text", "notifications" };
        return Resources.formatedResponse(status, properties);
    }

    @PutMapping("/setRead/{username}")
    public MappingJacksonValue setRead(@PathVariable String username,@RequestBody String notificationId,@RequestBody String isRead) {
        LOGGER.info("NotificationController >> setRead called to update read status.");
        DesireStatus status = appNotificationService.setReadStatus(username,Long.parseLong(notificationId), Boolean.parseBoolean(isRead));
        String[] properties = { "statusType", "text", "notification" };
        return Resources.formatedResponse(status, properties);
    }

    @GetMapping("/getCount/{username}")
    public MappingJacksonValue getCount(@PathVariable String username) {
        LOGGER.info("NotificationController >> getCount called to get unread notification count.");
        DesireStatus status = appNotificationService.getCount(username);
        String[] properties = { "statusType", "text", "totalRecord" };
        return Resources.formatedResponse(status, properties);
    }

    @PutMapping("/edit/{username}")
    public MappingJacksonValue editNotification(@PathVariable String username, @RequestBody AppNotification notification) {
        LOGGER.info("NotificationController >> editNotification called.");
        DesireStatus status = appNotificationService.editNotification(username, notification);
        String[] properties = { "statusType", "text", "notification" };
        return Resources.formatedResponse(status, properties);
    }

    @DeleteMapping("/delete/{notificationId}/{username}")
    public MappingJacksonValue deleteNotification(@PathVariable String username,
                                                  @PathVariable String notificationId) {
        LOGGER.info("NotificationController >> deleteNotification with notificationId >> " + notificationId + "called.");
        DesireStatus status = appNotificationService.deleteNotification(username, Long.parseLong(notificationId));
        String[] properties = { "statusType", "text", "" };
        return Resources.formatedResponse(status, properties);
    }

    @GetMapping("/id/{notificationId}/{username}")
    public MappingJacksonValue notification(String username,
                                            @PathVariable String notificationId) {
        LOGGER.info("NotificationController >> notification called for notification details.");
        DesireStatus status = appNotificationService.viewNotification(username,Long.parseLong(notificationId));
        String[] properties = { "statusType", "text", "notification" };
        return Resources.formatedResponse(status, properties);
    }




}
