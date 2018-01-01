package com.ysn.springmvc.repository;

import com.ysn.springmvc.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String lastName);

}
