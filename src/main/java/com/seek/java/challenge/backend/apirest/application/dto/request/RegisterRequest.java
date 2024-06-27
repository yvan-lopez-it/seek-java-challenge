package com.seek.java.challenge.backend.apirest.application.dto.request;

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
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;
}
