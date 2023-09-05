package com.ratedriverapp.ratedriver.services;

import com.ratedriverapp.ratedriver.dtos.UserDTO;
import com.ratedriverapp.ratedriver.exceptions.RateDriverException;
import com.ratedriverapp.ratedriver.models.User;
import com.ratedriverapp.ratedriver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ratedriverapp.ratedriver.mappers.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public UserDTO getUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User was not found"));
        return userMapper.mapUserToUserDTO(user);
    }

    public UserDTO searchUserByCarIdentityNumber(String carIdentityNumber){
        User user = userRepository.findByCarIdentityNumber(carIdentityNumber.trim());
        if(user==null){
            throw new RuntimeException("Could not find user by this car identity number");
        }
        return userMapper.mapUserToUserDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        validateUser(userDTO);
        User user = userMapper.mapUserDTOtoUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userMapper.mapUserToUserDTO(userRepository.save(user));
    }


    private void validateUser(UserDTO userDTO) {
        User sameEmailUser = userRepository.findByEmail(userDTO.getEmail());
        User sameCarIdentityNumber = userRepository.findByCarIdentityNumber(userDTO.getCarIdentityNumber());
        if (sameEmailUser != null) {
            throw new RateDriverException("Email already exists.");
        }
        if (userDTO.getUsername().length() < 3) {
            throw new RateDriverException("Username too short.");
        }
        if (sameCarIdentityNumber != null) {
            throw new RateDriverException("Car number already registered");
        }

    }


}
