package likelion.springsecurityexercise.controller;

import likelion.springsecurityexercise.domain.dto.*;
import likelion.springsecurityexercise.domain.dto.Response;
import likelion.springsecurityexercise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest joinRequest){
        UserDto userDto = userService.join(joinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(), userDto.getEmail()));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest loginRequest){
        String token = userService.login(loginRequest.getUserName(), loginRequest.getPassword());
        return Response.success(new UserLoginResponse(token));
    }
}
