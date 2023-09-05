package com.ratedriverapp.ratedriver.services;

import com.ratedriverapp.ratedriver.dtos.CreateReviewDTO;
import com.ratedriverapp.ratedriver.dtos.ReviewDTO;
import com.ratedriverapp.ratedriver.mappers.ReviewMapper;
import com.ratedriverapp.ratedriver.models.Review;
import com.ratedriverapp.ratedriver.models.User;
import com.ratedriverapp.ratedriver.repositories.ReviewRepository;
import com.ratedriverapp.ratedriver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;
    public ReviewDTO getReview(Integer reviewId){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review was not found"));
        return reviewMapper.mapReviewToReviewDTO(review);
    }
    public ReviewDTO createReview(CreateReviewDTO reviewDTO){
        validateRating(reviewDTO);
        User giver = userRepository.findById(reviewDTO.getGiverId()).orElseThrow(() -> new RuntimeException("Giver was not found"));
        User receiver = userRepository.findById(reviewDTO.getReceiverId()).orElseThrow(() -> new RuntimeException("Receiver was not found"));
        Review review = new Review();
        review.setStars(reviewDTO.getStars());
        review.setComment(reviewDTO.getComment());
        review.setGiver(giver);
        review.setReceiver(receiver);
        return reviewMapper.mapReviewToReviewDTO(reviewRepository.save(review));
    }

    public List<ReviewDTO> getReviewsByGiverId(Integer giverId) {
        List<Review> reviews = reviewRepository.findByGiverId(giverId);
        return reviews.stream()
                .map(reviewMapper::mapReviewToReviewDTO)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByReceiverId(Integer receiverId) {
        List<Review> reviews = reviewRepository.findByReceiverId(receiverId);
        return reviews.stream()
                .map(reviewMapper::mapReviewToReviewDTO)
                .collect(Collectors.toList());
    }

    private void validateRating(CreateReviewDTO createReviewDTO) {
        if (createReviewDTO.getComment().length() < 3){
            throw new RuntimeException("Comment length should be at least 3 characters.");
        }
        if(createReviewDTO.getComment().length() > 250){
            throw new RuntimeException("Comment length should be at 250 characters maximum.");
        }
        if (createReviewDTO.getStars() < 1) {
            throw new RuntimeException("Stars should be minimum 1.");
        }
        if (createReviewDTO.getStars() > 5) {
            throw new RuntimeException("Stars should be maximum 5.");
        }
    }

}
