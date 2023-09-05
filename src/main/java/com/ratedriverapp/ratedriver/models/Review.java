package com.ratedriverapp.ratedriver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reviews")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stars")
    private int stars;

    @Column(name = "comment")
    private String comment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "giver_id", nullable=false)
    private User giver;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable=false)
    private User receiver;

}
