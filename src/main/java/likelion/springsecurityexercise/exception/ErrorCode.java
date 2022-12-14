package likelion.springsecurityexercise.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "user name is duplicatied"),
    INTERNER_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error"),
    USER_NOT_FOUNDED(HttpStatus.NOT_FOUND, "not found error"),
    INVALID_ID_PASSWORD(HttpStatus.BAD_REQUEST, "bad Request");

    private HttpStatus httpStatus;
    private String message;

}
