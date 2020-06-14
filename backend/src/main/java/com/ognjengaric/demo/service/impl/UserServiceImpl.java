package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.repository.UserRepository;
import com.ognjengaric.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
