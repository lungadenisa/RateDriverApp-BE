package com.ratedriverapp.ratedriver.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private int id;

    private String username;

    private String email;

    private String password;

    private String carIdentityNumber;

    private double averageStars;
}
