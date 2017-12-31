package com.ysn.springmvc.service;

import com.ysn.springmvc.model.User;

import java.util.List;

public interface UserService {

    User findById(long id);

    User findByName(String name);

    void saveUser(User user);

    void udpateUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    void deleteAllUsers();

}
