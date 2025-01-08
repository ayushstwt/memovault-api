package com.ayshriv.memovault_api.service;

import com.ayshriv.memovault_api.common.DesireStatus;
import com.ayshriv.memovault_api.entities.User;

public interface UserService {
    DesireStatus addUser(User user);

    DesireStatus updateUser(User user);

    DesireStatus getAllUser();

    DesireStatus getUser(String emailId);
}
