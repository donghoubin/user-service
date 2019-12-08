package com.mike.user.controller;

import com.mike.user.entity.User;
import com.mike.user.model.UserActiveResponse;
import com.mike.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Mike Dong
 * @Date: 2019/12/3 20:47.
 */
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.findUserById(id);
        log.info("Get user successfully!");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.createUser(user);
        log.info("Create user successfully!");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity<Void> editUser(@RequestBody User user) {
        userService.editUser(user);
        log.info("Edit user successfully!");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserActiveResponse> activeUser(@PathVariable("id") long id) {
        UserActiveResponse userActiveResponse = new UserActiveResponse();
        try {
            log.info("Active user successfully!");
            userActiveResponse.setMessage("Active successfully");
            userActiveResponse.setResponseState("success");
            userService.activeUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Fail to active user" + e.getMessage());
            userActiveResponse.setMessage("Fail to active");
            userActiveResponse.setResponseState("fail");
        }
        return new ResponseEntity<>(userActiveResponse, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> retrieveAllCompanies() {
        List<User> userList = new ArrayList<>();
        userList = userService.retrieveAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
