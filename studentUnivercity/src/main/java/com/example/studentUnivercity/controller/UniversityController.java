package com.example.studentUnivercity.controller;


import com.example.studentUnivercity.DTO.UniversityRequestDTO;
import com.example.studentUnivercity.DTO.UniversityResponseDTO;
import com.example.studentUnivercity.exceptions.ResourceNotFoundException;
import com.example.studentUnivercity.model.University;
import com.example.studentUnivercity.service.UniversityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/input")
    public ResponseEntity<UniversityResponseDTO> createUniversity(@Valid @RequestBody UniversityRequestDTO universityRequestDTO){
        UniversityResponseDTO responseDTO = universityService.createUniversity(universityRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UniversityResponseDTO> getById(@PathVariable Long id){
        UniversityResponseDTO responseDTO = universityService.getById(id);
        return ResponseEntity.ok(responseDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityResponseDTO> updateUniversity(@PathVariable Long id, @RequestBody UniversityRequestDTO requestDTO) {
        UniversityResponseDTO responseDTO = universityService.updateUniversity(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UniversityResponseDTO> deleteUniversity(@PathVariable Long id){
        UniversityResponseDTO responseDTO = universityService.deleteUniversity(id);
        return ResponseEntity.ok().body(responseDTO);

    }


 }

