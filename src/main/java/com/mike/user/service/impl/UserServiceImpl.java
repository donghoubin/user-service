package com.mike.user.service.impl;

import com.mike.user.dao.UserRepository;
import com.mike.user.entity.User;
import com.mike.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: Mike Dong
 * @Date: 2019/12/3 20:47.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {

        user.setConfirmed('0');
        User result = userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(user.getEmail());
        message.setSubject("please click the link to active your account");
        message.setText("http://172.18.9.97:8081/userservice/user/"+result.getId());
        //message.setText("hello");
        mailSender.send(message);
    }

    @Override
    public List<User> retrieveAllUser() {
        return userRepository.findAllUser();
    }

    @Override
    @Transactional
    public void editUser(User user) {
        userRepository.updateUserById(user.getUserName(), user.getPassWord(),
                user.getUserType(), user.getEmail(), user.getMobileNumber(), user.getId());
    }

    @Override
    @Transactional
    public void activeUser(long id) {
        userRepository.activeUserById(id);
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }
}
