package likelion.springsecurityexercise.service;

import likelion.springsecurityexercise.domain.User;
import likelion.springsecurityexercise.domain.dto.UserDto;
import likelion.springsecurityexercise.domain.dto.UserJoinRequest;
import likelion.springsecurityexercise.domain.dto.UserJoinResponse;
import likelion.springsecurityexercise.exception.ErrorCode;
import likelion.springsecurityexercise.exception.HospitalReviewAppException;
import likelion.springsecurityexercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDto join(UserJoinRequest joinRequest) {
        userRepository.findByUserName(joinRequest.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, ErrorCode.DUPLICATED_USER_NAME.getMessage());
                });
        User savedUser = userRepository.save(joinRequest.toEntity());

        return UserDto.builder()
                .id(savedUser.getId())
                .password(savedUser.getPassword())
                .phone(savedUser.getPhone())
                .email(savedUser.getEmail())
                .build();
    }
}
