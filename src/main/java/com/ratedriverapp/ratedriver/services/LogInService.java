package com.ratedriverapp.ratedriver.services;

import com.ratedriverapp.ratedriver.dtos.UserDTO;
import com.ratedriverapp.ratedriver.mappers.UserMapper;
import com.ratedriverapp.ratedriver.models.User;
import com.ratedriverapp.ratedriver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogInService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO logIn(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if(!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())){
           throw new IllegalArgumentException("Password is not correct!");
        }
        return userMapper.mapUserToUserDTO(user);
    }

    public UserDTO registerUser(UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}

