package com.seek.java.challenge.backend.apirest.domain.service;

import com.seek.java.challenge.backend.apirest.application.dto.CandidateDTO;
import java.util.List;

public interface ICandidateService {

    List<CandidateDTO> getAllCandidates();

    CandidateDTO saveCandidate(CandidateDTO candidateDTO);

    CandidateDTO getCandidateById(Long id);

    CandidateDTO updateCandidate(Long id, CandidateDTO candidateDTO);

    void deleteCandidate(Long id);

}
