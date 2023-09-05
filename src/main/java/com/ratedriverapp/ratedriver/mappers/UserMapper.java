package com.ratedriverapp.ratedriver.mappers;

import com.ratedriverapp.ratedriver.dtos.UserDTO;
import com.ratedriverapp.ratedriver.models.Review;
import com.ratedriverapp.ratedriver.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.text.DecimalFormat;
import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "averageStars",expression = "java(getAverageStars(user))")
    UserDTO mapUserToUserDTO(User user);

    @Mapping(target = "givenReviews",ignore = true)
    @Mapping(target = "receivedReviews",ignore = true)
    @Mapping(target = "id",ignore = true)
    User mapUserDTOtoUser(UserDTO userDTO);
    default double getAverageStars(User user) {
        List<Review> receivedReviews = user.getReceivedReviews();
        if(receivedReviews == null || receivedReviews.isEmpty()){
            return 0;
        }else{
            DecimalFormat df = new DecimalFormat("#.00");
            return Double.parseDouble(df.format(receivedReviews.stream().mapToDouble(Review::getStars).sum()/user.getReceivedReviews().size()));
        }
    }
}
