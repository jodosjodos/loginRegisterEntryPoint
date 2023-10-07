package loginRegisterSystem.loginRegister.security.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class JwtConfig {
    private  String secret="jodos";

    private  int expiration=86400000;;

}
