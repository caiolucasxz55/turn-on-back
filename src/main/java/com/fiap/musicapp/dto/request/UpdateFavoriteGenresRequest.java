package com.fiap.musicapp.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdateFavoriteGenresRequest(
    @NotNull Set<Long> genreIds
) {}
