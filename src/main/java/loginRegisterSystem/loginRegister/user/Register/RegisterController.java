package loginRegisterSystem.loginRegister.user.Register;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Hidden
    @GetMapping("/test")
    ResponseEntity<?> Draft() {
        return ResponseEntity.ok().body("welcome to my  apis , this is  test api");
    }

    @PostMapping("/register")
    @Tag(name = "Register")
    @Operation(
            description = " Register user ",
            summary = "this is summary for  register user with email, password and username",
            responses = {
                    @ApiResponse(
                            description = "success  returns new user who have been created",
                            responseCode = "200"

                    ),
                    @ApiResponse(
                            description = "user with that email already exists",
                            responseCode = "409"

                    ),
                    @ApiResponse(
                            description = "provide invalid credentials",
                            responseCode = "401"

                    )
            }
    )
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
