package com.ysn.springmvc.controller;


import com.ysn.springmvc.model.base.Diagnostic;
import com.ysn.springmvc.model.user.User;
import com.ysn.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    /**
     * Retrieve all of users
     *
     * @return List all of users
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> listAllUsers() {
        Log("listAllUsers");
        Map<String, Object> mapData = new HashMap<>();
        List<User> users = new ArrayList<>();
        Diagnostic diagnostic = new Diagnostic(HttpStatus.OK, new Date().getTime());
        mapData.put("diagnostic", diagnostic);
        for (User user : userRepository.findAll()) {
            users.add(user);
        }
        mapData.put("data", users);
        return new ResponseEntity<>(mapData, HttpStatus.OK);
    }

    /**
     * Create a user
     *
     * @param user {User} Value of user
     * @return Result of executed with data user
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Log("createUer");
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Log message to printout in console
     *
     * @param message {String} Message for printout in console
     */
    private void Log(String message) {
        System.out.println(message);
    }
}
