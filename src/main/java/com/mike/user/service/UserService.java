package com.mike.user.service;

import com.mike.user.entity.User;

import java.util.List;

/**
 * @Description:
 * @Author: Mike Dong
 * @Date: 2019/12/3 20:47.
 */
public interface UserService {
    void createUser(User user);

    List<User> retrieveAllUser();

    void editUser(User user);

    void activeUser(long id);

    User findUserById(long id);
}
