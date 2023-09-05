package com.ratedriverapp.ratedriver.controllers;

import com.ratedriverapp.ratedriver.dtos.UserDTO;
import com.ratedriverapp.ratedriver.services.LogInService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LogInController {
    private final LogInService logInService;

    @PostMapping("login")
    public UserDTO logIn(@RequestBody UserDTO userDTO){
         return logInService.logIn(userDTO);
    }

    @PostMapping("register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO){
        return logInService.registerUser(userDTO);
    }
}
