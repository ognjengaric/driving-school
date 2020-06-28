package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.User;

import java.util.List;

public interface UserService {
    User findById(String id);
    List<User> findAll();
}
