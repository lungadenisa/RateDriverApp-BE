package com.ratedriverapp.ratedriver.mappers;

import com.ratedriverapp.ratedriver.dtos.ReviewDTO;
import com.ratedriverapp.ratedriver.models.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReviewMapper {

    @Mapping(target = "giver.averageStars",ignore = true)
    @Mapping(target = "receiver.averageStars",ignore = true)
    ReviewDTO mapReviewToReviewDTO(Review review);

    @Mapping(target = "giver",ignore = true)
    @Mapping(target = "receiver",ignore = true)
    Review mapReviewDTOtoReview(ReviewDTO reviewDTO);


}
