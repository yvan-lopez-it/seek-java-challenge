package com.seek.java.challenge.backend.apirest.domain.service.impl;

import com.seek.java.challenge.backend.apirest.adapters.out.CandidatePersistenceAdapter;
import com.seek.java.challenge.backend.apirest.application.dto.CandidateDTO;
import com.seek.java.challenge.backend.apirest.application.dto.CandidateMapper;
import com.seek.java.challenge.backend.apirest.domain.exception.ResourceNotFoundException;
import com.seek.java.challenge.backend.apirest.domain.model.Candidate;
import com.seek.java.challenge.backend.apirest.domain.service.ICandidateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements ICandidateService {

    private final CandidatePersistenceAdapter candidatePersistenceAdapter;
    private final CandidateMapper candidateMapper;

    @Autowired
    public CandidateServiceImpl(CandidatePersistenceAdapter candidatePersistenceAdapter, CandidateMapper candidateMapper) {
        this.candidatePersistenceAdapter = candidatePersistenceAdapter;
        this.candidateMapper = candidateMapper;
    }

    @Override
    public List<CandidateDTO> getAllCandidates() {
        List<Candidate> candidates = candidatePersistenceAdapter.getAllCandidates();
        return candidates.stream()
            .map(candidateMapper::toDTO)
            .toList();
    }

    @Override
    public CandidateDTO saveCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = candidateMapper.toDomain(candidateDTO);
        return candidateMapper.toDTO(candidatePersistenceAdapter.save(candidate));
    }

    @Override
    public CandidateDTO getCandidateById(Long id) {
        return candidateMapper.toDTO(candidatePersistenceAdapter.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found")));
    }

    @Override
    public CandidateDTO updateCandidate(Long id, CandidateDTO candidateDTO) {
        Candidate candidate = candidateMapper.toDomain(candidateDTO);
        return candidateMapper.toDTO(candidatePersistenceAdapter.update(id, candidate));
    }

    @Override
    public void deleteCandidate(Long id) {
        candidatePersistenceAdapter.deleteById(id);
    }


}
