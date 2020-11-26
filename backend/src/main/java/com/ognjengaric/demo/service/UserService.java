package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.dto.CourseProgressDTO;
import com.ognjengaric.demo.dto.NewUserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User save(User user);
    User save(NewUserDTO userDTO);
    User findById(Integer id);
    List<User> findAll();
    Page<User> getPageable(int page, int size);

    CourseProgressDTO getCourseProgress(String id);
}
