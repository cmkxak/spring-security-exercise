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

    public String login(String userName, String password) {
        //userName이 존재하는지 여부 확인
        userRepository.findByUserName(userName).orElseThrow(
                () -> new HospitalReviewAppException(ErrorCode.USER_NOT_FOUNDED,
                        String.format("%s는 가입한 적이 없습니다.", userName))
        );
        //password 일치하는지 확인


        //위의 두 조건들을 모두 통과하면 토큰 발행
        return "";
    }
}
