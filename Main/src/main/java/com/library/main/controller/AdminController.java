package com.library.main.controller;

import com.library.main.dto.UserRequest;
import com.library.main.model.User;
import com.library.main.repository.UserRepository;
import com.library.main.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/admin")
public class AdminController {
    private final Mapper mapper = new Mapper();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserRequest userRequest){
        //TODO authentication
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return new ResponseEntity<>(userRepository.save(mapper.toUser(userRequest)), HttpStatus.CREATED);
    }

}
