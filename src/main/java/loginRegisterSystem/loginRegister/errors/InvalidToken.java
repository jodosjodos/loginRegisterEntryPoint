package loginRegisterSystem.loginRegister.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = " invalid token please provide valid token")
public class InvalidToken extends  Exception{
    public  InvalidToken(String msg){
        super(msg);
    }
}
