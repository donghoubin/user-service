package com.mike.user.controller;

import com.mike.user.entity.User;
import com.mike.user.model.UserActiveResponse;
import com.mike.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @Author: Mike Dong
 * @Date: 2019/12/3 20:47.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity<Void> editUser(@RequestBody User user) {
        userService.editUser(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserActiveResponse> activeUser(@PathVariable("id") long id) {
        UserActiveResponse userActiveResponse = new UserActiveResponse();
        try {
            userActiveResponse.setMessage("Active successfully");
            userActiveResponse.setResponseState("success");
            userService.activeUser(id);
        } catch (Exception e) {
            e.printStackTrace();
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
