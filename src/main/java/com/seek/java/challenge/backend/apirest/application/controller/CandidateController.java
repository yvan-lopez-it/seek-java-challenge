package com.seek.java.challenge.backend.apirest.application.controller;

import com.seek.java.challenge.backend.apirest.application.dto.CandidateDTO;
import com.seek.java.challenge.backend.apirest.domain.service.ICandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidates")
@Tag(name = "Candidates", description = "Endpoints for managing candidates")
public class CandidateController {

    private final ICandidateService candidateService;

    @Autowired
    public CandidateController(ICandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    @Operation(summary = "Get all candidates")
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
        List<CandidateDTO> candidates = candidateService.getAllCandidates();
        if (candidates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new candidate")
    public ResponseEntity<CandidateDTO> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        return ResponseEntity.ok(candidateService.saveCandidate(candidateDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a candidate by ID")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable Long id) {
        return ResponseEntity.ok(candidateService.getCandidateById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a candidate by ID")
    public ResponseEntity<CandidateDTO> updateCandidate(@PathVariable Long id, @RequestBody CandidateDTO candidateDTO) {
        return ResponseEntity.ok(candidateService.updateCandidate(id, candidateDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a candidate by ID")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }
}
