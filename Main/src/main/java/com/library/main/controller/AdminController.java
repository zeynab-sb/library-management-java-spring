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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@RestController
@Validated
@RequestMapping("/admin")
public class AdminController {
    private final Mapper mapper = new Mapper();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public ModelAndView addUser(@Valid @RequestBody UserRequest userRequest){
        //TODO authentication
        try {
            System.out.println("adddd useeeeeeeeeeeeeeeeeer");
        userRequest.setEnabled(true);
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(mapper.toUser(userRequest));
        return new ModelAndView("redirect:" + "http://localhost:9090/admin/users");

        } catch (Exception e) {
            return new ModelAndView("500");
        }
    }

    @GetMapping("/users/{id}")
    public ModelAndView deleteUser(@PathVariable("id") long id) {
        //TODO authentication
        try {

            RestTemplate restTemplate = new RestTemplate();
            String clearSessionURL = "http://localhost:8080/auth/clear";
            ResponseEntity<String> response = restTemplate.getForEntity(clearSessionURL + "/" + id, String.class);

            if(response.getStatusCode().equals(HttpStatus.OK)){
                userRepository.deleteById(id);
            }

            return new ModelAndView("redirect:" + "http://localhost:9090/admin/users");
        } catch (Exception e) {
            return new ModelAndView("500");
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody UserRequest userRequest) {
        //TODO authentication
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setUsername(userRequest.getUsername());
            _user.setPassword(userRequest.getPassword());
            _user.setAuthority(userRequest.getAuthority());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public ModelAndView getAllUsers() {
        //TODO authentication
        try {
            List<User> users = new ArrayList<>(userRepository.findAll());
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("admin");
            modelAndView.addObject ( "users", users);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("500");
        }
    }
    @GetMapping("/401")
    public ModelAndView get401() {
        return new ModelAndView("401");
    }


}
