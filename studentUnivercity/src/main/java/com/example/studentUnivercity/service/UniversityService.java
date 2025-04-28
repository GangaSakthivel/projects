package com.example.studentUnivercity.service;

import com.example.studentUnivercity.DTO.UniversityRequestDTO;
import com.example.studentUnivercity.DTO.UniversityResponseDTO;
import com.example.studentUnivercity.exceptions.UniversityNotFoundException;
import com.example.studentUnivercity.model.University;
import com.example.studentUnivercity.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public List<UniversityResponseDTO> getAllUniversity() {
        //  Fetch all universities from the repository
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

    public UniversityResponseDTO createUniversity(UniversityRequestDTO universityRequestDTO) {
        //request to entity

        University university = new University();
        university.setUniversityName(universityRequestDTO.getUniversityName());
        university.setLocation(universityRequestDTO.getLocation());
        university.setEstablishedYear(universityRequestDTO.getEstablishedYear());
        university.setWebsite(universityRequestDTO.getWebsite());
        university.setEmail(universityRequestDTO.getEmail());
        university.setPhoneNumber(universityRequestDTO.getPhoneNumber());
        university.setUniversityType(universityRequestDTO.getUniversityType());

        //saving to the university entity
        University savedUniversity = universityRepository.save(university);

        //entity to response
        UniversityResponseDTO responseDTO = new UniversityResponseDTO();
        responseDTO.setId(savedUniversity.getId());
        responseDTO.setUniversityName(savedUniversity.getUniversityName());
        responseDTO.setLocation(savedUniversity.getLocation());
        responseDTO.setEstablishedYear(savedUniversity.getEstablishedYear());
        responseDTO.setWebsite(savedUniversity.getWebsite());
        responseDTO.setPhoneNumber(savedUniversity.getPhoneNumber());
        responseDTO.setUniversityType(savedUniversity.getUniversityType());

        return responseDTO;
    }

    public UniversityResponseDTO getById(Long id) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new UniversityNotFoundException("University with ID " + id + " not found"));

        UniversityResponseDTO responseDTO = new UniversityResponseDTO();

        responseDTO.setId(university.getId());
        responseDTO.setUniversityName(university.getUniversityName());
        responseDTO.setLocation(university.getLocation());
        responseDTO.setEstablishedYear(university.getEstablishedYear());
        responseDTO.setWebsite(university.getWebsite());
        responseDTO.setEmail(university.getEmail());
        responseDTO.setPhoneNumber(university.getPhoneNumber());
        responseDTO.setUniversityType(university.getUniversityType());

        return responseDTO;

    }
}
