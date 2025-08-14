package com.forohub.alura.foro.Dto;

import jakarta.validation.constraints.NotBlank;



public record LoginDTO(
        @NotBlank String email,
        @NotBlank String password
) {}
