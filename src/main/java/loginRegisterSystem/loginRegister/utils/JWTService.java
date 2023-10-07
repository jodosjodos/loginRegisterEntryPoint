package loginRegisterSystem.loginRegister.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import loginRegisterSystem.loginRegister.errors.InvalidToken;
import loginRegisterSystem.loginRegister.security.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTService {
    JwtConfig jwtConfig;

    @Autowired
    public JWTService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(String email) {

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtConfig.getExpiration());
        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }

    public boolean validateToken(String token) throws InvalidToken {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw  new InvalidToken(" invalid token , please provide valid token");
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
