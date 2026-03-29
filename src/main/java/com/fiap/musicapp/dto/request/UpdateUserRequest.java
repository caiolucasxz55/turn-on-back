package com.fiap.musicapp.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
    @NotBlank String name
) {}
