package com.ratedriverapp.ratedriver.controllers;

import com.ratedriverapp.ratedriver.dtos.UserDTO;
import com.ratedriverapp.ratedriver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @GetMapping("get-user/{userId}")
    public UserDTO getUser(@PathVariable Integer userId){
        return userService.getUser(userId);
    }
    @GetMapping("get-by-car-number")
    public UserDTO searchUserByCarIdentityNumber(@RequestParam("carIdentityNumber") String carIdentityNumber){
        return userService.searchUserByCarIdentityNumber(carIdentityNumber);
    }
    @PostMapping("create")
    public void createUser(@RequestBody UserDTO user){
        userService.createUser(user);
    }

}
