package com.seek.java.challenge.backend.apirest.adapters.out;

import com.seek.java.challenge.backend.apirest.application.dto.CandidateMapper;
import com.seek.java.challenge.backend.apirest.domain.exception.ResourceNotFoundException;
import com.seek.java.challenge.backend.apirest.domain.model.Candidate;
import com.seek.java.challenge.backend.apirest.infrastructure.persistence.entity.CandidateEntity;
import com.seek.java.challenge.backend.apirest.infrastructure.persistence.repository.CandidateRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CandidatePersistenceAdapter {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    public CandidatePersistenceAdapter(CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
    }

    public List<Candidate> getAllCandidates() {
        List<CandidateEntity> candidateEntities = candidateRepository.findAll();
        return candidateEntities.stream()
            .map(candidateMapper::toDomain)
            .toList();
    }

    public Candidate save(Candidate candidate) {
        CandidateEntity entity = candidateMapper.toEntity(candidate);
        return candidateMapper.toDomain(candidateRepository.save(entity));
    }

    public Optional<Candidate> findById(Long id) {
        return candidateRepository.findById(id)
            .map(candidateMapper::toDomain);
    }

    public Candidate update(Long id, Candidate candidate) {
        if (!candidateRepository.existsById(id)) {
            throw new ResourceNotFoundException("Candidate not found");
        }
        CandidateEntity entity = candidateMapper.toEntity(candidate);
        entity.setId(id);
        return candidateMapper.toDomain(candidateRepository.save(entity));
    }

    public void deleteById(Long id) {
        candidateRepository.deleteById(id);
    }

}
