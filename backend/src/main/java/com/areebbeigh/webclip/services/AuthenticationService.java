package com.areebbeigh.webclip.services;

import com.areebbeigh.webclip.dtos.Credentials;
import com.areebbeigh.webclip.dtos.UserDetailsAuthDto;
import com.areebbeigh.webclip.entities.UserEntity;
import com.areebbeigh.webclip.respositories.UserRepository;
import com.areebbeigh.webclip.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            JwtUtil jwtUtil,
            UserRepository userRepository,
            AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public Authentication authenticate(Credentials credentials) {
        UserEntity userEntity = userRepository.findByUsername(credentials.getUsername());
        if (userEntity == null) return null;
        UserDetails userDetails = new UserDetailsAuthDto();
        BeanUtils.copyProperties(userEntity, userDetails);
        return authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDetails, credentials.getPassword())
        );
    }

    public String generateJwt(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        Objects.requireNonNull(principal);
        return jwtUtil.createTokenFor(
            ((UserDetails)principal).getUsername()
        );
    }

    public UserEntity getCurrentUserEntity() {
        final Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) return null;
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties((UserDetails)user, userEntity);
        return userEntity;
    }
}
