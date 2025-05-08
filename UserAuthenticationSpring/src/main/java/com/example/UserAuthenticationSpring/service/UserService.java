package com.example.UserAuthenticationSpring.service;


import com.example.UserAuthenticationSpring.dto.LoginRequestDTO;
import com.example.UserAuthenticationSpring.dto.LoginResponseDTO;
import com.example.UserAuthenticationSpring.model.User;
import com.example.UserAuthenticationSpring.repository.UserRepository;
import com.example.UserAuthenticationSpring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getId());
        return new LoginResponseDTO(user.getId(), token);
    }



}
