package com.seek.java.challenge.backend.apirest.application.dto;

import com.seek.java.challenge.backend.apirest.domain.model.Candidate;
import com.seek.java.challenge.backend.apirest.infrastructure.persistence.entity.CandidateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    CandidateDTO toDTO(Candidate candidate);
    Candidate toDomain(CandidateDTO candidateDTO);
    CandidateEntity toEntity(Candidate candidate);
    Candidate toDomain(CandidateEntity candidateEntity);

}
