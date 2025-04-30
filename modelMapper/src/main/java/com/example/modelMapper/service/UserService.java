package com.example.modelMapper.service;

import com.example.modelMapper.config.ModelMapperConfig;
import com.example.modelMapper.converter.UserDTOConverter;

import com.example.modelMapper.dto.UserDTO;
import com.example.modelMapper.model.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {


    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
