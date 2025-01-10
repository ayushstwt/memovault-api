package com.ayshriv.memovault_api.service.impl;

import com.ayshriv.memovault_api.common.Constants;
import com.ayshriv.memovault_api.common.DesireStatus;
import com.ayshriv.memovault_api.common.Resources;
import com.ayshriv.memovault_api.entities.User;
import com.ayshriv.memovault_api.repository.UserRepository;
import com.ayshriv.memovault_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.ayshriv.memovault_api.service.impl.JournalEntryServiceImpl.LOGGER;

@Service
public class UserServiceImpl implements UserService {

    Logger logger=Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Override
    public DesireStatus addUser(User user) {
        LOGGER.info("UserService >> addUser called!");
        DesireStatus status = new DesireStatus();
        Date dtNow = new Date();

        try {
            // Set default fields for the new user
            user.setCreatedOn(dtNow);
            user.setUpdatedOn(dtNow);
            user.setActive(true);

            // Save the user in the repository
            User savedUser = userRepository.save(user);
            if (savedUser != null) {
                status = Resources.setStatus(Constants.STATUS_SUCCESS, Constants.SAVE_SUCCESS, "User");
                status.setUser(savedUser);
            } else {
                status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.SAVE_FAILURE, "User");
            }
        } catch (Exception e) {
            status = Resources.setStatus(Constants.STATUS_ERROR, Constants.EXECUTION_ERROR + e.getMessage(), "Add user");
        }
        return status;
    }

    @Override
    public DesireStatus getAllUser() {
        LOGGER.info("UserService >> getAllUser called!");
        DesireStatus status = new DesireStatus();

        try {
            List<User> users = userRepository.findAll();
            if (!users.isEmpty()) {
                status = Resources.setStatus(Constants.STATUS_SUCCESS, Constants.LIST_SUCCESS, "Users");
                status.setUsers(users);
            } else {
                status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.LIST_FAILURE, "Users");
            }
        } catch (Exception e) {
            status = Resources.setStatus(Constants.STATUS_ERROR, Constants.EXECUTION_ERROR + e.getMessage(), "Get all users");
        }

        return status;
    }

    @Override
    public DesireStatus getUser(String emailId) {
        LOGGER.info("UserService >> getUser called with emailId: {}", emailId);
        DesireStatus status = new DesireStatus();

        try {
            // Search for the user by emailId
            User foundUser = userRepository.findByEmailId(emailId);
            if (foundUser != null) {
                // If user is found, return success status
                status = Resources.setStatus(Constants.STATUS_SUCCESS, Constants.OBJ_EXIST, "User");
                status.setUser(foundUser);
            } else {
                // If user is not found, return failure status
                status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.OBJ_NOT_EXIST, "User");
            }
        } catch (Exception e) {
            // Handle any unexpected errors
            status = Resources.setStatus(Constants.STATUS_ERROR, Constants.EXECUTION_ERROR + e.getMessage(), "Get user");
            LOGGER.error("Error in UserService >> getUser: {}", e.getMessage());
        }

        return status;
    }

    @Override
    public DesireStatus deleteUser(String email) {
        LOGGER.info("UserService >> delete User called with emailId: {}", email);
        DesireStatus status = new DesireStatus();
        try {
            User foundUser = userRepository.findByEmailId(email);
            if (foundUser != null) {
                userRepository.delete(foundUser);
                status = Resources.setStatus(Constants.STATUS_SUCCESS, Constants.DELETE_SUCCESS, "User");
            } else {
                status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.OBJ_NOT_EXIST, "User");
            }
        } catch (Exception e) {
            status = Resources.setStatus(Constants.STATUS_ERROR, Constants.EXECUTION_ERROR + e.getMessage(), "Delete user");
            LOGGER.error("Error in UserService >> deleteUser: {}", e.getMessage());
        }
        return status;
    }

    @Override
    public DesireStatus updateUser(User user) {
        LOGGER.info("UserService >> updateUser called with emailId: {}", user.getEmailId());
        DesireStatus status = new DesireStatus();

        try {
            // Search for the user by emailId
            User existingUser = userRepository.findByEmailId(user.getEmailId());
            if (existingUser != null) {

                // Update user fields
                existingUser.setFirstName((user.getFirstName() != null ? user.getFirstName() : existingUser.getFirstName()));
                existingUser.setLastName((user.getLastName() != null ? user.getLastName() : existingUser.getLastName()));
                existingUser.setMobileNo(user.getMobileNo() != null ? user.getMobileNo() : existingUser.getMobileNo());
                existingUser.setUpdatedOn(new Date());

                // Save updated user
                User updatedUser = userRepository.save(existingUser);

                // Return success status
                status = Resources.setStatus(Constants.STATUS_SUCCESS, Constants.UPDATE_SUCCESS, "User");
                status.setUser(updatedUser);
            } else {
                // If user is not found, return failure status
                status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.OBJ_NOT_EXIST, "User");
            }
        } catch (Exception e) {
            // Handle any unexpected errors
            status = Resources.setStatus(Constants.STATUS_ERROR, Constants.EXECUTION_ERROR + e.getMessage(), "Update user");
            LOGGER.error("Error in UserService >> updateUser: {}", e.getMessage());
        }

        return status;
    }

}
