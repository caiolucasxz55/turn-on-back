package com.fiap.musicapp.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PlaylistRequest(
    @NotBlank String name,
    String description
) {}
