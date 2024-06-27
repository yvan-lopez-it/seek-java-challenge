package com.seek.java.challenge.backend.apirest.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CandidateDTO {

    private Long id;
    private String name;
    private String email;
    private String gender;
    private Double salary;
    private Double expected;
}
