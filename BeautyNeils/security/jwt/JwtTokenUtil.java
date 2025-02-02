package BeautyNeils.security.jwt;

import BeautyNeils.database.Role;
import BeautyNeils.database.dto.RegisterDto;
import BeautyNeils.database.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secjret}")
    private  String secret;

    @Value("${jwt.lifetime}")
    private  Duration jwtLifetime;

    public String generateToken(RegisterDto registerDto) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("roles", Role.USER.getAuthority());
        claims.put("email", registerDto.getEmail());

        Date issuedDate = new Date();
        Date expiryDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());

        return Jwts.builder()
                .claims(claims)
                .subject(registerDto.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
