package com.userAuthentication.service;

import com.userAuthentication.dto.BaseResponse;
import com.userAuthentication.dto.UserRequestDTO;
import com.userAuthentication.model.Role;
import com.userAuthentication.model.User;
import com.userAuthentication.repository.RoleRepository;
import com.userAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public BaseResponse<String> registerUser(UserRequestDTO requestDTO) {
        if (userRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())) {
            return new BaseResponse<>(false, "Phone number already registered", null);
        }

        User user = new User();
        user.setUserName(requestDTO.getUserName());
        user.setPhoneNumber(requestDTO.getPhoneNumber());
        user.setUserEmail(requestDTO.getUserEmail());
        user.setSalaryType(requestDTO.getSalaryType());
        user.setSalary(requestDTO.getSalary());
        user.setAddress(requestDTO.getAddress());
        user.setStatus(requestDTO.isStatus());
        user.setNotes(requestDTO.getNotes());

        // Encode password
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        // Set Role (assuming Role entity is already present)
        Optional<Role> role = roleRepository.findByName(requestDTO.getRoleName());
        if (role.isEmpty()) {
            return new BaseResponse<>(false, "Invalid role provided", null);
        }
        user.setRoles((List<Role>) Collections.singleton(role.get()));

        try {
            if (requestDTO.getPhoto() != null && !requestDTO.getPhoto().isEmpty()) {
                user.setPhoto(requestDTO.getPhoto().getBytes());
            }

            if (requestDTO.getDocument() != null && !requestDTO.getDocument().isEmpty()) {
                user.setDocument(requestDTO.getDocument().getBytes());
            }
        } catch (IOException e) {
            return new BaseResponse<>(false, "Error reading uploaded files", null);
        }

        userRepository.save(user);

        return new BaseResponse<>(true, "User registered successfully", null);
    }




}

