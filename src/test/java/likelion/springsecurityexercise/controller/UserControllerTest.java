package likelion.springsecurityexercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import likelion.springsecurityexercise.domain.dto.UserDto;
import likelion.springsecurityexercise.domain.dto.UserJoinRequest;
import likelion.springsecurityexercise.domain.dto.UserLoginRequest;
import likelion.springsecurityexercise.exception.ErrorCode;
import likelion.springsecurityexercise.exception.HospitalReviewAppException;
import likelion.springsecurityexercise.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입_성공_테스트")
    void join_success() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("홍길수")
                .password("rlfehd1")
                .email("gildong1@naver.com")
                .phone("010-2019-2911")
                .build();

        when(userService.join(any())).thenReturn(mock(UserDto.class));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("회원가입_실패_테스트")
    void join_failed() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("홍길수")
                .password("rlfehd1")
                .email("gildong1@naver.com")
                .phone("010-2019-2911")
                .build();

        //join() 메서드를 실행할 경우 HospitalReviewAPpException 예외가 터진다.
        when(userService.join(any())).thenThrow(new HospitalReviewAppException(
                ErrorCode.DUPLICATED_USER_NAME, ErrorCode.DUPLICATED_USER_NAME.getMessage()
        ));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("로그인 실패 테스트 - userName 없음")
    @WithMockUser
    void login_fail() throws Exception {
        UserLoginRequest loginRequest = UserLoginRequest.builder()
                .userName("chulmin")
                .password("123sdf")
                .build();

        //가정
        when(userService.login(any(), any())).thenThrow(new HospitalReviewAppException(ErrorCode.USER_NOT_FOUNDED, ""));

        //mock을 통한 테스트
        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(loginRequest)))
                .andDo(print())
                .andExpect(status().isNotFound());

    }
}