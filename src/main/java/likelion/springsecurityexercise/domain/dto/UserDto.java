package likelion.springsecurityexercise.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 비즈니스 계층과 프리젠테이션 계층 간의 데이터 전달 역할을 위한 객체
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private String phone;
    private String email;
}
