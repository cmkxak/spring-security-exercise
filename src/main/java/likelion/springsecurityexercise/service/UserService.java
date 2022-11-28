package likelion.springsecurityexercise.service;

import likelion.springsecurityexercise.domain.User;
import likelion.springsecurityexercise.domain.dto.UserDto;
import likelion.springsecurityexercise.domain.dto.UserJoinRequest;
import likelion.springsecurityexercise.domain.dto.UserJoinResponse;
import likelion.springsecurityexercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDto join(UserJoinRequest joinRequest) {
        userRepository.findByUserName(joinRequest.getUserName())
                .ifPresent(user -> {
                    throw new RuntimeException("해당 id를 가진 유저가 존재합니다.");
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
