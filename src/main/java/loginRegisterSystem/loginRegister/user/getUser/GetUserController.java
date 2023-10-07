package loginRegisterSystem.loginRegister.user.getUser;

import loginRegisterSystem.loginRegister.errors.InvalidToken;
import loginRegisterSystem.loginRegister.errors.UserNotExists;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class GetUserController {
    private final GetUserService getUserService;

    @GetMapping("/getUser")
    ResponseEntity<?> getUserWithToken(@RequestHeader("token") String token) {
        try {
            return ResponseEntity.ok().body(getUserService.getUSer(token));

        } catch (InvalidToken e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (UserNotExists e) {
            return ResponseEntity.badRequest().body("user with that email doesn't exists");
        }
    }
}
