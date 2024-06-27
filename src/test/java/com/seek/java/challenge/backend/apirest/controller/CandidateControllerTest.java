package com.seek.java.challenge.backend.apirest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.seek.java.challenge.backend.apirest.application.controller.CandidateController;
import com.seek.java.challenge.backend.apirest.application.dto.CandidateDTO;
import com.seek.java.challenge.backend.apirest.domain.exception.ResourceNotFoundException;
import com.seek.java.challenge.backend.apirest.domain.service.ICandidateService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CandidateControllerTest {

    @Mock
    private ICandidateService candidateService;

    @InjectMocks
    private CandidateController candidateController;

    @BeforeEach
    public void setup() {
        // Optional: Initialize mock data or behaviors common to all tests
    }

    @Test
    void testGetAllCandidates_EmptyList() {
        // Mock behavior
        when(candidateService.getAllCandidates()).thenReturn(Collections.emptyList());

        // Call controller method
        ResponseEntity<List<CandidateDTO>> response = candidateController.getAllCandidates();

        // Verify response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllCandidates_NonEmptyList() {
        // Prepare test data
        List<CandidateDTO> candidates = Arrays.asList(
            new CandidateDTO(1L, "John Doe", "john.doe@example.com", "Male", 50000.0, 60000.0),
            new CandidateDTO(2L, "Jane Smith", "jane.smith@example.com", "Female", 60000.0, 65000.0)
        );

        // Mock behavior
        when(candidateService.getAllCandidates()).thenReturn(candidates);

        // Call controller method
        ResponseEntity<List<CandidateDTO>> response = candidateController.getAllCandidates();

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidates, response.getBody());
    }

    @Test
    void testCreateCandidate() {
        CandidateDTO candidateDTO = new CandidateDTO(1L, "John Doe", "john.doe@example.com", "Male", 50000.0, 60000.0);

        when(candidateService.saveCandidate(any(CandidateDTO.class))).thenReturn(candidateDTO);

        ResponseEntity<CandidateDTO> response = candidateController.createCandidate(candidateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(candidateDTO, response.getBody());
    }

    @Test
    void testGetCandidateByIdFound() {
        Long candidateId = 1L;
        CandidateDTO candidateDTO = new CandidateDTO(candidateId, "John Doe", "john.doe@example.com", "Male", 50000.0, 60000.0);

        when(candidateService.getCandidateById(candidateId)).thenReturn(candidateDTO);

        ResponseEntity<CandidateDTO> response = candidateController.getCandidateById(candidateId);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(candidateDTO, response.getBody());
    }

    @Test
    void testGetCandidateByIdNotFound() {
        Long candidateId = 1L;

        when(candidateService.getCandidateById(candidateId)).thenThrow(ResourceNotFoundException.class);

        ResponseEntity<CandidateDTO> response = candidateController.getCandidateById(candidateId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateCandidate() {
        Long candidateId = 1L;
        CandidateDTO updatedDTO = new CandidateDTO(candidateId, "John Doe", "john.doe@example.com", "Male", 60000.0, 65000.0);

        when(candidateService.updateCandidate(eq(candidateId), any(CandidateDTO.class))).thenReturn(updatedDTO);

        ResponseEntity<CandidateDTO> response = candidateController.updateCandidate(candidateId, updatedDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDTO, response.getBody());
    }

    @Test
    void testUpdateCandidateNotFound() {
        Long candidateId = 1L;
        CandidateDTO updatedDTO = new CandidateDTO(candidateId, "John Doe", "john.doe@example.com", "Male", 60000.0, 65000.0);

        when(candidateService.updateCandidate(eq(candidateId), any(CandidateDTO.class))).thenThrow(ResourceNotFoundException.class);

        ResponseEntity<CandidateDTO> response = candidateController.updateCandidate(candidateId, updatedDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteCandidate() {
        Long candidateId = 1L;

        ResponseEntity<Void> response = candidateController.deleteCandidate(candidateId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(candidateService, times(1)).deleteCandidate(candidateId);
    }
}
