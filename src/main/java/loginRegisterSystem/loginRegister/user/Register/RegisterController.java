package loginRegisterSystem.loginRegister.user.Register;

import loginRegisterSystem.loginRegister.errors.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class RegisterController {
RegisterService registerService;
    @GetMapping("/test")
    ResponseEntity<?> Draft() {
        return ResponseEntity.ok().body("welcome to my  apis , this is  test api");
    }

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok().body(registerService.registerUser(request));
        } catch (UserAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A user with that email address already exists", HttpStatus.CONFLICT));
        } catch (InvalidPassword e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Please enter a strong password", HttpStatus.BAD_REQUEST));
        } catch (InvalidUsername e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Please enter a username with a minimum length of 4", HttpStatus.BAD_REQUEST));
        } catch (InvalidEmail e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Please enter a valid email", HttpStatus.BAD_REQUEST));
        }
    }


}
