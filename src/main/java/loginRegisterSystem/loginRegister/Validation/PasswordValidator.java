package loginRegisterSystem.loginRegister.Validation;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class PasswordValidator implements Predicate<String> {
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z\\d])(?=.{6,})[^\\s]*$";

    @Override
    public boolean test(String password) {
        System.out.println(password.matches(PASSWORD_REGEX));
        return password != null && password.matches(PASSWORD_REGEX);
    }
}
