package com.userAuthentication.service;

import com.userAuthentication.dto.UserRequestDTO;
import com.userAuthentication.dto.UserResponseDTO;
import com.userAuthentication.model.Role;
import com.userAuthentication.model.User;
import com.userAuthentication.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Set;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    private User toEntity(UserRequestDTO requestDTO) {
        User user = new User();
        user.setUserName(requestDTO.getUserName());
        user.setPassword(requestDTO.getPassword()); // WARNING: Encode in production!
        user.setPhoneNumber(requestDTO.getPhoneNumber());
        user.setUserEmail(requestDTO.getUserEmail());
        user.setSalaryType(requestDTO.getSalaryType());
        user.setSalary(requestDTO.getSalary());
        user.setAddress(requestDTO.getAddress());
        user.setStatus(requestDTO.isStatus());
        user.setNotes(requestDTO.getNotes());
        user.setRoles((Set<Role>) requestDTO.getRoles()); // Set the roles

        // Handle photo upload
        MultipartFile photoFile = requestDTO.getPhoto();
        if (photoFile != null && !photoFile.isEmpty()) {
            try {
                user.setPhoto(photoFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error while reading photo file", e);
            }
        }

        // Handle document upload
        MultipartFile documentFile = requestDTO.getDocument();
        if (documentFile != null && !documentFile.isEmpty()) {
            try {
                user.setDocument(documentFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error while reading document file", e);
            }
        }

        return user;
    }


    private UserResponseDTO convertToUserResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUserName(user.getUserName());
        responseDTO.setPhoneNumber(user.getPhoneNumber());
        responseDTO.setUserEmail(user.getUserEmail());
        responseDTO.setSalaryType(user.getSalaryType());
        responseDTO.setSalary(user.getSalary());
        responseDTO.setAddress(user.getAddress());
        responseDTO.setStatus(user.isStatus());
        responseDTO.setNotes(user.getNotes());

        String baseUrl = "http://195.35.23.17:8080/api/user"; // Adjust your base URL

        // Generate photo URL if photo data exists
        if (user.getPhoto() != null && user.getPhoto().length > 0) {
            responseDTO.setPhotoUrl(baseUrl + "/" + user.getId() + "/photo");
        } else {
            responseDTO.setPhotoUrl(null); // No photo available
        }

        // Generate document URL if document data exists
        if (user.getDocument() != null && user.getDocument().length > 0) {
            responseDTO.setDocumentUrl(baseUrl + "/" + user.getId() + "/document");
        } else {
            responseDTO.setDocumentUrl(null); // No document available
        }

        return responseDTO;
    }


    public UserResponseDTO createUser(UserRequestDTO requestDTO, HttpServletRequest servletRequest) {
        // 1. Basic Input Validation
        if (requestDTO.getUserName() == null || requestDTO.getUserName().trim().isEmpty() ||
                requestDTO.getPassword() == null || requestDTO.getPassword().trim().isEmpty() ||
                requestDTO.getPhoneNumber() == null || requestDTO.getPhoneNumber().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username, password, and phone number are required.");
        }

        // 2. Check for Duplicate Username or Phone Number
        if (userRepository.findByUserName(requestDTO.getUserName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists.");
        }
        if (userRepository.findByPhoneNumber(requestDTO.getPhoneNumber()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists.");
        }

        // 3. Convert Request DTO to User Entity
        User newUser = toEntity(requestDTO);

        // 4. Save the new user to the database
        try {
            User savedUser = userRepository.save(newUser);
            // 5. Convert the saved User entity to UserResponseDTO
            return convertToUserResponseDTO(savedUser);
        } catch (Exception dbException) {
            System.err.println("Error saving user to database: " + dbException.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save user to the database.");
        }
    }
}

