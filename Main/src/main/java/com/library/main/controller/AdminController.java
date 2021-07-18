package com.library.main.controller;

import com.library.main.dto.UserRequest;
import com.library.main.model.User;
import com.library.main.repository.UserRepository;
import com.library.main.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView addUser(@RequestParam Map<String, String> userRequest){
        //TODO authentication
        try { User user = new User();
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userRequest.get("password")));
        user.setUsername(userRequest.get("username"));
        user.setAuthority(userRequest.get("authority"));
        userRepository.save(user);
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

    @RequestMapping(value="/users/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView updateUser(@PathVariable("id") long id, @RequestParam Map<String, String> userRequest) {
        //TODO authentication
        System.out.println("caaal shoood");
        Optional<User> userData = userRepository.findById(id);
try {
    if (userData.isPresent()) {
        User _user = userData.get();
        _user.setUsername(userRequest.get("username"));
        _user.setPassword(userRequest.get("password"));
        _user.setAuthority(userRequest.get("authority"));
        userRepository.save(_user);
        return new ModelAndView("redirect:" + "http://localhost:9090/admin/users");
    } else {
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ModelAndView("404");

    }
}catch(Exception e){
    e.printStackTrace();
    return new ModelAndView("500");
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
