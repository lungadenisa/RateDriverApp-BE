package com.ratedriverapp.ratedriver.dtos;

import lombok.Data;

@Data
public class CreateReviewDTO {
    private int stars;

    private String comment;

    private int giverId;

    private int receiverId;
}
