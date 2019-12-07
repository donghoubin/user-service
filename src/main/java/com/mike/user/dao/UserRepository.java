package com.mike.user.dao;

import com.mike.user.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Description:
 * @Author: Mike Dong
 * @Date: 2019/12/3 20:48.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("from User")
    List<User> findAllUser();

    @Query("from User where id=?1")
    User findUserById(long id);

    @Modifying
    @Query("update User u set u.userName=?1, u.passWord=?2, u.userType=?3, u.email=?4, u.mobileNumber=?5 where u.id=?6")
    void updateUserById(String userName, String passWord, String userType, String email, String mobileNumber, long id);

    @Modifying
    @Query("update User u set u.confirmed=1 where u.id=?1")
    void activeUserById(long id);

}
