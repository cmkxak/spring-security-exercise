package likelion.springsecurityexercise.domain.dto;

import likelion.springsecurityexercise.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;
    private String phone;

    public User toEntity(String password) {
        return User.builder()
                .userName(userName)
                .password(password)
                .email(email)
                .phone(phone)
                .build();
    }
}
