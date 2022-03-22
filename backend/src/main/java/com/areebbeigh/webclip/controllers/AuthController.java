package com.areebbeigh.webclip.controllers;

import com.areebbeigh.webclip.dtos.Credentials;
import com.areebbeigh.webclip.dtos.UserDetailsAuthDto;
import com.areebbeigh.webclip.services.AuthenticationService;
import com.areebbeigh.webclip.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
@Slf4j
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(
            @RequestBody @Valid Credentials credentials,
            HttpServletResponse response) {
        // Authenticate credentials
        log.info("Received login req: " + credentials.toString());
        Authentication auth = authenticationService.authenticate(credentials);
        log.info("Authentication: " + auth);
        // Generate session jwt and send as response cookie
        final String token = authenticationService.generateJwt(auth);
        final Cookie cookie = new Cookie("jwt", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody @Valid UserDetailsAuthDto userDetails) {
        log.info("Register: " + userDetails);
        userService.create(userDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
