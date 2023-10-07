package loginRegisterSystem.loginRegister.user.Register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
public class RegisterRequest {
    private final String username;
    private final String email;
    private final String password;
}
