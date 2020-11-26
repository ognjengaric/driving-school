package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.dto.NewUserDTO;
import com.ognjengaric.demo.dto.UserDTO;
import com.ognjengaric.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Value("${backend_uri}")
    private String backendUri;

    @GetMapping(params = { "page", "size" })
    public Page<UserDTO> getPageable(@RequestParam("page") int page, @RequestParam("size") int size){
        return userService.getPageable(page, size).map(UserDTO::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        User user = userService.findById(Integer.parseInt(id));

        if(user == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody NewUserDTO newUserDTO){
        User user = userService.save(newUserDTO);

        if(user == null)
            return ResponseEntity.badRequest().build();

        URI uri = URI.create(backendUri + "/user/" + user.getId());

        return ResponseEntity.created(uri).build();
    }

}
