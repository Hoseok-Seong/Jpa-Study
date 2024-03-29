package shop.mtcoding.servicebank.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.mtcoding.servicebank.dto.ResponseDTO;

// 서버 에러
@Getter
public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }

    public ResponseDTO<?> body(){
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        responseDTO.fail(HttpStatus.INTERNAL_SERVER_ERROR, "serverError", getMessage());
        return responseDTO;
    }

    public HttpStatus status(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
