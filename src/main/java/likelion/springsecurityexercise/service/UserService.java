package likelion.springsecurityexercise.service;

import likelion.springsecurityexercise.domain.dto.UserDto;
import likelion.springsecurityexercise.domain.dto.UserJoinRequest;
import likelion.springsecurityexercise.domain.dto.UserJoinResponse;

public class UserService {
    public UserDto join(UserJoinRequest joinRequest) {
        return new UserDto("","", "", "");
    }
}
