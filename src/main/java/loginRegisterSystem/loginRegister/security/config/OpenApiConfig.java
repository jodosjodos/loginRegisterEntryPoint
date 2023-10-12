package loginRegisterSystem.loginRegister.security.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "jodos",
                        email = "jeandedieu2030@gmail.com",
                        url = "https://github.com/jodosjodos"
                ),
                description = " springboot login and registration  user with simple spring security",
                title = "user login and registration API",
                version = "1.0",
                license = @License(
                        name = "linkedIn profile",
                        url = "https://www.linkedin.com/in/jean-de-dieu-nshimyumukiza-97b315259/"
                )

        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://github.com/jodosjodos"
                )

        }
)
  //@SecurityScheme(
//        name = "bearerAuth ",
//        description = "JWT auth description",
//        scheme = "bearer",
//        type = SecuritySchemeType.APIKEY,
//        bearerFormat = "JWT",
//        in = SecuritySchemeIn.HEADER
//)
public class OpenApiConfig {
}
