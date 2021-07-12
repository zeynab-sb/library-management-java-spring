package com.library.main.controller;

import com.library.main.dto.UserRequest;
import com.library.main.model.User;
import com.library.main.repository.UserRepository;
import com.library.main.utils.Mapper;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/admin")
public class AdminController {
    private Mapper mapper = new Mapper();

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserRequest userRequest){
        //TODO authentication
            return new ResponseEntity<>(userRepository.save(mapper.toUser(userRequest)), HttpStatus.CREATED);
    }

}
