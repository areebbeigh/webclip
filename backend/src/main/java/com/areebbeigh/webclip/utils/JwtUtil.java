package com.areebbeigh.webclip.utils;

import com.areebbeigh.webclip.services.UserDetailService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.issuer}")
    private String issuer;
    @Value("${security.jwt.validity}")
    private Long tokenValidityInDays = 1L;
    private Algorithm algorithm;
    private JWTVerifier verifier;
    private final UserDetailService userDetailService;

    public JwtUtil(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostConstruct
    private void postConstruct() {
        this.algorithm = Algorithm.HMAC256(this.secret);
        this.verifier = JWT.require(this.algorithm).withIssuer(this.issuer).build();
    }

    public UserDetails verifyToken(String token) throws JWTVerificationException {
        DecodedJWT decoded = this.verifier.verify(token);
        String username = decoded.getSubject();
        return userDetailService.loadUserByUsername(username);
    }

    public String createTokenFor(String username) {
        return JWT
                .create()
                .withIssuer(this.issuer)
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(
                        new Date(
                            System.currentTimeMillis() + tokenValidityInDays * 24 * 3600 * 1000
                        )
                )
                .sign(this.algorithm);
    }
}
