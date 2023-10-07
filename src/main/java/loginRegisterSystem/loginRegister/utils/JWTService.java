package loginRegisterSystem.loginRegister.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JWTService {
//    @Value("jodos")
    public String jwtSecurity = "jodos";
//    @Value("${jwt.expiration}")
    public long expirationDate =86400000;

    public String generateToken(String email) {

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expirationDate);
        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecurity)
                .compact();
    }
}
