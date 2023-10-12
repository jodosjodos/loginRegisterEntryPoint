package loginRegisterSystem.loginRegister.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;

@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        XorCsrfTokenRequestAttributeHandler xor =
                new XorCsrfTokenRequestAttributeHandler();
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(
                                "/api/v1/user/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .anyRequest()
                        .authenticated()
        );
        return http.build();
    }
}
