package com.example.studentUnivercity.controller;


import com.example.studentUnivercity.DTO.UniversityRequestDTO;
import com.example.studentUnivercity.DTO.UniversityResponseDTO;
import com.example.studentUnivercity.model.University;
import com.example.studentUnivercity.service.UniversityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;


    @GetMapping
    public ResponseEntity<List<UniversityResponseDTO>> getAllUniversity(){
        List<UniversityResponseDTO> universityList = universityService.getAllUniversity();
        return ResponseEntity.ok(universityList);

    }

    public ResponseEntity<UniversityResponseDTO> createUniversity(@Valid @RequestBody UniversityRequestDTO universityRequestDTO){
        UniversityResponseDTO responseDTO = universityService.createUniversity(universityRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

 }

