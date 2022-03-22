package com.areebbeigh.webclip.services;

import com.areebbeigh.webclip.dtos.UserDetailsAuthDto;
import com.areebbeigh.webclip.entities.UserEntity;
import com.areebbeigh.webclip.respositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserDetailsAuthDto userDetails) {
        UserEntity newUser = new UserEntity();
        BeanUtils.copyProperties(userDetails, newUser);
        newUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userRepository.save(newUser);
    }
}
