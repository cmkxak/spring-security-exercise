package likelion.springsecurityexercise.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class HospitalReviewAppException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    @Override
    public String toString(){
        if (message == null) return errorCode.getMessage();
        return String.format("%s, %s", errorCode.getMessage(), message);
    }
}
