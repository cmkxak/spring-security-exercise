package likelion.springsecurityexercise.domain.dto;

import likelion.springsecurityexercise.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;
    private String phone;

    public User toEntity() {
        return User.builder()
                .userName(userName)
                .password(password)
                .email(email)
                .phone(phone)
                .build();
    }
}
