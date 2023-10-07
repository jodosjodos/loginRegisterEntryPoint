package loginRegisterSystem.loginRegister.Validation;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class EmailValidator implements Predicate<String> {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public boolean test(String email) {
        return  email !=null &&  email.matches(EMAIL_REGEX);
    }
}
