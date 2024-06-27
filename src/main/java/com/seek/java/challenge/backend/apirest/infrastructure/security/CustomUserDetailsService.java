package com.seek.java.challenge.backend.apirest.infrastructure.security;

import com.seek.java.challenge.backend.apirest.infrastructure.persistence.entity.UserEntity;
import com.seek.java.challenge.backend.apirest.infrastructure.persistence.repository.UserRepository;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }
}
