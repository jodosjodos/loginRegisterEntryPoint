package loginRegisterSystem.loginRegister.user.Register;

import loginRegisterSystem.loginRegister.Validation.EmailValidator;
import loginRegisterSystem.loginRegister.Validation.PasswordValidator;
import loginRegisterSystem.loginRegister.errors.InvalidEmail;
import loginRegisterSystem.loginRegister.errors.InvalidPassword;
import loginRegisterSystem.loginRegister.errors.InvalidUsername;
import loginRegisterSystem.loginRegister.errors.UserAlreadyExists;
import loginRegisterSystem.loginRegister.user.User;
import loginRegisterSystem.loginRegister.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;

    public User registerUser(RegisterRequest request) throws UserAlreadyExists, InvalidPassword, InvalidUsername, InvalidEmail {
        if (request.getUsername() == null || request.getUsername().length() < 4) {
            throw new InvalidUsername();
        }

        boolean exists = userRepository.findByEmail(request.getEmail()).isPresent();
        if (exists) throw new UserAlreadyExists();

        //        validate password
        if (request.getPassword() == null || !passwordValidator.test(request.getPassword())) {
            throw new InvalidPassword();
        }
//         validating email
        if (request.getEmail() == null || !emailValidator.test(request.getEmail())) {
            throw new InvalidEmail();
        }

//         validating username


        return userRepository.save(new User(request.getUsername(), request.getEmail(), bCryptPasswordEncoder.encode(request.getPassword())));
    }

}
