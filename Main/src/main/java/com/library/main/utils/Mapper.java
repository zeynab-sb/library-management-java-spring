package com.library.main.utils;

import com.library.main.dto.UserRequest;
import com.library.main.model.User;

public class Mapper {

    public User toUser(UserRequest userRequest){
        return new User(userRequest.getUsername(), userRequest.getPassword(), userRequest.getAuthority(), userRequest.getEnabled());
    }

}
