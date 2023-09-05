package com.ratedriverapp.ratedriver.repositories;

import com.ratedriverapp.ratedriver.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByGiverId(Integer giverId);
    List<Review> findByReceiverId(Integer receiverId);

}
