package com.seek.java.challenge.backend.apirest.infrastructure.persistence.repository;

import com.seek.java.challenge.backend.apirest.infrastructure.persistence.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

}
