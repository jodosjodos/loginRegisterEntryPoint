package loginRegisterSystem.loginRegister.user.getUser;

import loginRegisterSystem.loginRegister.errors.InvalidToken;
import loginRegisterSystem.loginRegister.errors.UserNotExists;
import loginRegisterSystem.loginRegister.user.User;
import loginRegisterSystem.loginRegister.user.UserRepository;
import loginRegisterSystem.loginRegister.utils.JWTService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class GetUserService {
    private JWTService jwtService;
    private UserRepository userRepository;

    @Autowired // Optional if you use constructor injection
    public GetUserService(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public Optional<User> getUSer(String token) throws InvalidToken, UserNotExists {
        token = token.trim();
        if (!token.contains(" "))
            throw new InvalidToken(" please start your token with Bearer keyword and add space");
        token = token.split(" ")[1];
        boolean isValid = jwtService.validateToken(token);
        if (!isValid) throw new InvalidToken("invalid token , please provide valid token");
        String userEmail = jwtService.getUsernameFromToken(token);
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (user.isEmpty()) throw new UserNotExists();
        return user;
    }
}


