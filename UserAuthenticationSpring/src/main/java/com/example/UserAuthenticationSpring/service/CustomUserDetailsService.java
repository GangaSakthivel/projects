package com.example.UserAuthenticationSpring.service;

import com.example.UserAuthenticationSpring.model.Status;
import com.example.UserAuthenticationSpring.model.User;
import com.example.UserAuthenticationSpring.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; // Don't forget this annotation

import java.util.Collection;
import java.util.stream.Collectors;

@Service // Mark this as a Spring service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        //map roles to authorities
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
//                user.getStatus() == Status.ACTIVE, // enabled based on status
//                true, // accountNonExpired (default)
//                true, // credentialsNonExpired (default)
//                true, // accountNonLocked (default)
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .collect(Collectors.toList())
        );
    }
}