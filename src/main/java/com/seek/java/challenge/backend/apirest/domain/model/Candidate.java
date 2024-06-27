package com.seek.java.challenge.backend.apirest.domain.model;

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
public class Candidate {

    private Long id;
    private String name;
    private String email;
    private String gender;
    private Double salary;
    private Double expected;

}
