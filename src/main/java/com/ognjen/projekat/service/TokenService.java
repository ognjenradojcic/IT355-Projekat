package com.ognjen.projekat.service;

import com.ognjen.projekat.model.Tokens;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final String SECRET_KEY = "472D4B6150645367566B5970337336763979244226452948404D625165546857";
    private static final Integer ACCESS_TOKEN_DURATION = 1000 * 60 * 10;
    private static final Integer REFRESH_TOKEN_DURATION = 1000 * 60 * 60 * 24;

    private final UserService userService;

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(jwtToken);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {

        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Tokens refreshTokens(String refreshToken) {

        var user = userService.getByUsername(extractUsername(refreshToken));

        String accessToken = generateToken(user, ACCESS_TOKEN_DURATION);

        return new Tokens(accessToken, refreshToken);
    }

    public Tokens generateTokens(UserDetails userDetails) {

        String accessToken = generateToken(userDetails, ACCESS_TOKEN_DURATION);
        String refreshToken = generateToken(userDetails, REFRESH_TOKEN_DURATION);

        return new Tokens(accessToken, refreshToken);
    }

    private String generateToken(UserDetails userDetails, Integer duration) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + duration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {

        final String username = extractUsername(jwtToken);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }
}
