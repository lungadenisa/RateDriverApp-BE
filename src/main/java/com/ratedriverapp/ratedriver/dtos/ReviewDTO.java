package com.ratedriverapp.ratedriver.dtos;

import lombok.Data;

@Data
public class ReviewDTO {

    private int id;

    private int stars;

    private String comment;

    private UserDTO giver;

    private UserDTO receiver;

}
