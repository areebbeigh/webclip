package com.areebbeigh.webclip.services;

import com.areebbeigh.webclip.dtos.UserDetailsAuthDto;
import com.areebbeigh.webclip.entities.UserEntity;
import com.areebbeigh.webclip.respositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        return new UserDetailsAuthDto(user.getId(), user.getUsername(), user.getPassword());
    }
}
