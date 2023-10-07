package loginRegisterSystem.loginRegister.user.login;

import loginRegisterSystem.loginRegister.Validation.EmailValidator;
import loginRegisterSystem.loginRegister.Validation.PasswordValidator;
import loginRegisterSystem.loginRegister.errors.InvalidCredentials;
import loginRegisterSystem.loginRegister.errors.InvalidEmail;
import loginRegisterSystem.loginRegister.errors.InvalidPassword;
import loginRegisterSystem.loginRegister.errors.UserNotExists;
import loginRegisterSystem.loginRegister.user.User;
import loginRegisterSystem.loginRegister.user.UserRepository;
import loginRegisterSystem.loginRegister.utils.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {
    EmailValidator emailValidator;
    PasswordValidator passwordValidator;
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    JWTService jwtService;

    public String loginUser(LoginRequest request) throws InvalidEmail, InvalidPassword, UserNotExists, InvalidCredentials {

        if (request.getEmail() == null || !emailValidator.test(request.getEmail())) {
            throw new InvalidEmail();
        }
        if (request.getPassword() == null || !passwordValidator.test(request.getPassword())) {
            throw new InvalidPassword();
        }
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            boolean passwordMatches = bCryptPasswordEncoder.matches(request.getPassword(), user.get().getPassword());
            if (passwordMatches) {
                return jwtService.generateToken(user.get().getEmail());
            } else {
                throw new InvalidCredentials();
            }
        } else {
            throw new UserNotExists();
        }

    }
}
