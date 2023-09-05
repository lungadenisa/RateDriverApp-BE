package com.ratedriverapp.ratedriver.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "car_identity_number")
    private String carIdentityNumber;

    @OneToMany(mappedBy = "giver",fetch = FetchType.EAGER)
    private List<Review> givenReviews;

    @OneToMany(mappedBy = "receiver",fetch = FetchType.EAGER)
    private List<Review> receivedReviews;

}
