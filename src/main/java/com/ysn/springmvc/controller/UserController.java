package com.ysn.springmvc.controller;


import com.ysn.springmvc.model.base.Diagnostic;
import com.ysn.springmvc.model.user.User;
import com.ysn.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Diagnostic diagnostic = new Diagnostic(HttpStatus.OK.value(), HttpStatus.OK.name(), new Date().getTime());
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
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        Log("createUer");
        Map<String, Object> mapDataReturn = new HashMap<>();
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setUnix_timestamp(new Date().getTime());
        boolean isValid = true;
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            diagnostic.setMessage("Field name is required.");
            isValid = false;
        } else if (user.getAge() == 0) {
            diagnostic.setMessage("Field age is required.");
            isValid = false;
        } else if (user.getSalary() == 0) {
            diagnostic.setMessage("Field salary is required.");
            isValid = false;
        }

        if (!isValid) {
            diagnostic.setStatus(HttpStatus.BAD_REQUEST.value());
            mapDataReturn.put("diagnostic", diagnostic);
            return new ResponseEntity<>(mapDataReturn, HttpStatus.BAD_REQUEST);
        } else {
            diagnostic.setStatus(HttpStatus.CREATED.value());
            diagnostic.setMessage(HttpStatus.CREATED.name());
            mapDataReturn.put("diagnostic", diagnostic);
        }
        mapDataReturn.put("data", user);
        userRepository.save(user);
        return new ResponseEntity<>(mapDataReturn, HttpStatus.CREATED);
    }

    /**
     * Update a user
     *
     * @param id {long} ID of a user
     * @param user {User} Value a user
     * @return Result of executed with data user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Log("updateUser");
        Map<String, Object> mapDataReturn = new HashMap<>();

        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setUnix_timestamp(new Date().getTime());
        User userLocal = userRepository.findOne(id);
        if (userLocal == null) {
            diagnostic.setStatus(HttpStatus.NO_CONTENT.value());
            diagnostic.setMessage(HttpStatus.NO_CONTENT.name());
            mapDataReturn.put("diagnostic", diagnostic);
            return new ResponseEntity<>(mapDataReturn, HttpStatus.NO_CONTENT);
        }

        boolean isFieldValid = true;
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            isFieldValid = false;
            diagnostic.setStatus(HttpStatus.BAD_REQUEST.value());
            diagnostic.setMessage("Field name is required.");
        } else if (user.getAge() == 0) {
            isFieldValid = false;
            diagnostic.setStatus(HttpStatus.BAD_REQUEST.value());
            diagnostic.setMessage("Field age is required.");
        } else if (user.getSalary() == 0) {
            isFieldValid = false;
            diagnostic.setStatus(HttpStatus.BAD_REQUEST.value());
            diagnostic.setMessage("Field salary is required.");
        }
        if (!isFieldValid) {
            mapDataReturn.put("diagnostic", diagnostic);
            return new ResponseEntity<>(mapDataReturn, HttpStatus.BAD_REQUEST);
        }

        userLocal.setName(user.getName());
        userLocal.setAge(user.getAge());
        userLocal.setSalary(user.getSalary());
        userRepository.save(userLocal);
        diagnostic.setStatus(HttpStatus.OK.value());
        diagnostic.setMessage(HttpStatus.OK.name());
        mapDataReturn.put("diagnostic", diagnostic);
        mapDataReturn.put("data", userLocal);
        return new ResponseEntity<>(mapDataReturn, HttpStatus.OK);
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
