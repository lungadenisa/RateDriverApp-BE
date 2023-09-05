package com.ratedriverapp.ratedriver.controllers;

import com.ratedriverapp.ratedriver.dtos.CreateReviewDTO;
import com.ratedriverapp.ratedriver.dtos.ReviewDTO;
import com.ratedriverapp.ratedriver.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("get-review/{reviewId}")
    public ReviewDTO getReview(@PathVariable Integer reviewId){return reviewService.getReview(reviewId);}

    @PostMapping("create")
    public ReviewDTO createReview(@RequestBody CreateReviewDTO review){
        return reviewService.createReview(review);
    }

    @GetMapping("get-reviews-by-giver/{giverId}")
    public List<ReviewDTO> getReviewsByGiverId(@PathVariable Integer giverId) {
        return reviewService.getReviewsByGiverId(giverId);
    }

    @GetMapping("get-reviews-by-receiver/{receiverId}")
    public List<ReviewDTO> getReviewsByReceiverId(@PathVariable Integer receiverId) {
        return reviewService.getReviewsByReceiverId(receiverId);
    }
}
