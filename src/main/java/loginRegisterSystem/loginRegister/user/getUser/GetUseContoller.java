package loginRegisterSystem.loginRegister.user.getUser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class GetUseContoller {

    @GetMapping("/getUser")
    ResponseEntity<?> getUser(@RequestHeader("token") String token){
      return   ResponseEntity.ok().body(token);
    }
}
