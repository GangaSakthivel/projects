package com.example.studentUnivercity.service;

import com.example.studentUnivercity.DTO.UniversityRequestDTO;
import com.example.studentUnivercity.DTO.UniversityResponseDTO;
import com.example.studentUnivercity.model.University;
import com.example.studentUnivercity.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public List<UniversityResponseDTO> getAllUniversity() {
        // Step 1: Fetch all universities from the repository
        List<University> universities = universityRepository.findAll();

        // Step 2: Convert to UniversityResponseDTO using Stream API
        List<UniversityResponseDTO> universityResponseDTOs = universities.stream()
                .map(university -> new UniversityResponseDTO(
                        university.getId(),
                        university.getUniversityName(),
                        university.getLocation(),
                        university.getEstablishedYear(),
                        university.getWebsite(),
                        university.getEmail(),
                        university.getPhoneNumber(),
                        university.getUniversityType()
                ))
                .collect(Collectors.toList());

        // Step 3: Return the converted list
        return universityResponseDTOs;
    }

}
