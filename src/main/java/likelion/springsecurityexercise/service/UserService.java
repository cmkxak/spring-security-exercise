package likelion.springsecurityexercise.service;

import likelion.springsecurityexercise.domain.dto.UserDto;
import likelion.springsecurityexercise.domain.dto.UserJoinRequest;
import likelion.springsecurityexercise.domain.dto.UserJoinResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto join(UserJoinRequest joinRequest) {
        return new UserDto("","", "", "");
    }
}
