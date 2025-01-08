package com.ayshriv.memovault_api.controller;

import com.ayshriv.memovault_api.common.DesireStatus;
import com.ayshriv.memovault_api.common.Resources;
import com.ayshriv.memovault_api.entities.User;
import com.ayshriv.memovault_api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public MappingJacksonValue addUser(@RequestBody User user) {
        LOGGER.info("UserController >> addUser called.");
        DesireStatus status = userService.addUser(user);
        String[] properties = { "statusType", "text", "user" };
        return Resources.formatedResponse(status, properties);
    }

    @PutMapping("/update")
    public MappingJacksonValue updateUser(@RequestBody User user) {
        LOGGER.info("UserController >> updateUser called.");
        DesireStatus status = userService.updateUser(user);
        String[] properties = { "statusType", "text", "user" };
        return Resources.formatedResponse(status, properties);
    }

    @GetMapping("/getAll")
    public MappingJacksonValue getAllUser() {
        LOGGER.info("UserController >> getAllUser called.");
        DesireStatus status = userService.getAllUser();
        String[] properties = { "statusType", "text", "users" };
        return Resources.formatedResponse(status, properties);
    }

    @GetMapping("/get/{email}")
    public MappingJacksonValue getUser(@PathVariable String email) {
        LOGGER.info("UserController >> getUser called.");
        DesireStatus status = userService.getUser(email);
        String[] properties = { "statusType", "text", "user" };
        return Resources.formatedResponse(status, properties);
    }
}
