package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.User;

public interface UserService {
    User findById(Long id);
}
