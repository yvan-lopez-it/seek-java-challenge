package com.seek.java.challenge.backend.apirest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.seek.java.challenge.backend.apirest.adapters.out.CandidatePersistenceAdapter;
import com.seek.java.challenge.backend.apirest.application.dto.CandidateDTO;
import com.seek.java.challenge.backend.apirest.application.dto.CandidateMapper;
import com.seek.java.challenge.backend.apirest.domain.exception.ResourceNotFoundException;
import com.seek.java.challenge.backend.apirest.domain.model.Candidate;
import com.seek.java.challenge.backend.apirest.domain.service.impl.CandidateServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CandidateServiceTest {

    @Mock
    private CandidatePersistenceAdapter candidatePersistenceAdapter;

    @Mock
    private CandidateMapper candidateMapper;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCandidates() {
        // Mock data
        Candidate candidate1 = new Candidate(1L, "John Doe", "john.doe@example.com", "Male", 5000.0, 6000.0);
        Candidate candidate2 = new Candidate(2L, "Jane Smith", "jane.smith@example.com", "Female", 6000.0, 7000.0);
        List<Candidate> candidates = Arrays.asList(candidate1, candidate2);

        // Mock behavior
        when(candidatePersistenceAdapter.getAllCandidates()).thenReturn(candidates);
        when(candidateMapper.toDTO(any(Candidate.class)))
            .thenAnswer(invocation -> {
                Candidate candidate = invocation.getArgument(0);
                return new CandidateDTO(candidate.getId(), candidate.getName(), candidate.getEmail(),
                    candidate.getGender(), candidate.getSalary(), candidate.getExpected());
            });

        // Call service method
        List<CandidateDTO> candidateDTOs = candidateService.getAllCandidates();

        // Verify results
        assertEquals(2, candidateDTOs.size());
        assertEquals(candidate1.getName(), candidateDTOs.get(0).getName());
        assertEquals(candidate2.getName(), candidateDTOs.get(1).getName());

        // Verify interactions
        verify(candidatePersistenceAdapter, times(1)).getAllCandidates();
        verify(candidateMapper, times(2)).toDTO(any(Candidate.class));
    }

    @Test
    void testSaveCandidate() {
        // Mock data
        CandidateDTO candidateDTO = new CandidateDTO(null, "John Doe", "john.doe@example.com", "Male", 5000.0, 6000.0);
        Candidate candidateToSave = new Candidate(null, "John Doe", "john.doe@example.com", "Male", 5000.0, 6000.0);
        Candidate savedCandidate = new Candidate(1L, "John Doe", "john.doe@example.com", "Male", 5000.0, 6000.0);

        // Mock behavior
        when(candidateMapper.toDomain(candidateDTO)).thenReturn(candidateToSave);
        when(candidatePersistenceAdapter.save(candidateToSave)).thenReturn(savedCandidate);
        when(candidateMapper.toDTO(savedCandidate)).thenReturn(candidateDTO);

        // Call service method
        CandidateDTO savedDTO = candidateService.saveCandidate(candidateDTO);

        // Verify results
        assertEquals(savedCandidate.getName(), savedDTO.getName());

        // Verify interactions
        verify(candidateMapper, times(1)).toDomain(candidateDTO);
        verify(candidatePersistenceAdapter, times(1)).save(candidateToSave);
        verify(candidateMapper, times(1)).toDTO(savedCandidate);
    }

    @Test
    void testGetCandidateById() {
        // Mock data
        Long candidateId = 1L;
        Candidate candidate = new Candidate(candidateId, "John Doe", "john.doe@example.com", "Male", 5000.0, 6000.0);

        // Mock behavior
        when(candidatePersistenceAdapter.findById(candidateId)).thenReturn(Optional.of(candidate));
        when(candidateMapper.toDTO(candidate)).thenReturn(new CandidateDTO(candidate.getId(), candidate.getName(),
            candidate.getEmail(), candidate.getGender(), candidate.getSalary(), candidate.getExpected()));

        // Call service method
        CandidateDTO foundDTO = candidateService.getCandidateById(candidateId);

        // Verify results
        assertEquals(candidate.getName(), foundDTO.getName());

        // Verify interactions
        verify(candidatePersistenceAdapter, times(1)).findById(candidateId);
        verify(candidateMapper, times(1)).toDTO(candidate);
    }

    @Test
    void testUpdateCandidate() {
        // Mock data
        Long candidateId = 1L;
        CandidateDTO candidateDTO = new CandidateDTO(candidateId, "John Doe", "john.doe@example.com", "Male", 5000.0, 6000.0);
        Candidate updatedCandidate = new Candidate(candidateId, "John Doe", "john.doe@example.com", "Male", 5000.0, 6000.0);

        // Mock behavior
        when(candidateMapper.toDomain(candidateDTO)).thenReturn(updatedCandidate);
        when(candidatePersistenceAdapter.update(candidateId, updatedCandidate)).thenReturn(updatedCandidate);
        when(candidateMapper.toDTO(updatedCandidate)).thenReturn(candidateDTO);

        // Call service method
        CandidateDTO updatedDTO = candidateService.updateCandidate(candidateId, candidateDTO);

        // Verify results
        assertEquals(candidateDTO.getName(), updatedDTO.getName());

        // Verify interactions
        verify(candidateMapper, times(1)).toDomain(candidateDTO);
        verify(candidatePersistenceAdapter, times(1)).update(candidateId, updatedCandidate);
        verify(candidateMapper, times(1)).toDTO(updatedCandidate);
    }

    @Test
    void testDeleteCandidate() {
        // Mock data
        Long candidateId = 1L;

        // Call service method
        candidateService.deleteCandidate(candidateId);

        // Verify interactions
        verify(candidatePersistenceAdapter, times(1)).deleteById(candidateId);
    }

    @Test
    void testGetCandidateById_NotFound() {
        // Mock data
        Long candidateId = 1L;

        // Mock behavior
        when(candidatePersistenceAdapter.findById(candidateId)).thenReturn(Optional.empty());

        // Call service method and verify exception
        try {
            candidateService.getCandidateById(candidateId);
        } catch (ResourceNotFoundException ex) {
            assertEquals("Candidate not found", ex.getMessage());
        }

        // Verify interactions
        verify(candidatePersistenceAdapter, times(1)).findById(candidateId);
    }
}
