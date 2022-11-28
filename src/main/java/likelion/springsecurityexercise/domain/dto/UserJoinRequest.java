package likelion.springsecurityexercise.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
}
