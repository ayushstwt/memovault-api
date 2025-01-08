package com.ayshriv.memovault_api.repository;

import com.ayshriv.memovault_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailId(String emailId);
    User findByMobileNo(String mobileNo);
    User findByFirstNameAndLastName(String firstName, String lastName);
}
