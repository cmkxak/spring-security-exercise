package likelion.springsecurityexercise.service;

import likelion.springsecurityexercise.domain.User;
import likelion.springsecurityexercise.domain.dto.UserDto;
import likelion.springsecurityexercise.domain.dto.UserJoinRequest;
import likelion.springsecurityexercise.exception.ErrorCode;
import likelion.springsecurityexercise.exception.HospitalReviewAppException;
import likelion.springsecurityexercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserDto join(UserJoinRequest joinRequest) {
        userRepository.findByUserName(joinRequest.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, ErrorCode.DUPLICATED_USER_NAME.getMessage());
                });
        User savedUser = userRepository.save(joinRequest.toEntity(encoder.encode(joinRequest.getPassword())));

        return UserDto.builder()
                .id(savedUser.getId())
                .phone(savedUser.getPhone())
                .email(savedUser.getEmail())
                .build();
    }
}
