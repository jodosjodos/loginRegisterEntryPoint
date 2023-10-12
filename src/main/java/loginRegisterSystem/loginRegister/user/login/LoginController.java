package loginRegisterSystem.loginRegister.user.login;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import loginRegisterSystem.loginRegister.errors.InvalidCredentials;
import loginRegisterSystem.loginRegister.errors.InvalidEmail;
import loginRegisterSystem.loginRegister.errors.InvalidPassword;
import loginRegisterSystem.loginRegister.errors.UserNotExists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Data
@RestController
@RequestMapping("/api/v1/user")
public class LoginController {
 LoginService loginService;
   @PostMapping("/login")
   @Tag( name = "Login")
   @Operation(
           description = " Login user ",
           summary = "this is summary for how  login work you must provide password and email",
           responses = {
                   @ApiResponse(
                           description = "success it return token yu can use for validation",
                           responseCode = "200"

                   ),
                   @ApiResponse(
                           description = "invalid credentials",
                           responseCode = "401"

                   )
           }
   )
    ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            return ResponseEntity.ok().body("{ token : "+loginService.loginUser(loginRequest) + " }");
        } catch (InvalidEmail e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please enter valid format of  email");
        } catch (UserNotExists | InvalidCredentials e) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");
        } catch (InvalidPassword e) {
            return  ResponseEntity.badRequest().body("please enter valid password");
        }

   }
}
