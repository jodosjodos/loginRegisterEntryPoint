package loginRegisterSystem.loginRegister.user.getUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Get user")
    @Operation(
            description = " Get user ",
            summary = "this is summary for  retrieving user credentials by using  valid Bearer token note that you must start your token with 'Bearer' keyword",
            responses = {
                    @ApiResponse(
                            description = "success  returns  user based on provided credentials",
                            responseCode = "200"

                    ),
                    @ApiResponse(
                            description = "user with that email already exists",
                            responseCode = "409"

                    ),
                    @ApiResponse(
                            description = "provide invalid token like when you have modified token",
                            responseCode = "401"

                    )
            }
    )
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
