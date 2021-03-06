package com.library.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Credential {
    @NotEmpty(message = "username is required")
    @Size(min = 3, max = 15, message = "username isn't valid")
    private String username;

    @NotEmpty(message = "password is required")
    private String password;
}
