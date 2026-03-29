package com.fiap.musicapp.dto.response;

import com.fiap.musicapp.entity.Genre;

public record GenreResponse(Long id, String name, String slug) {
    public static GenreResponse from(Genre g) {
        return new GenreResponse(g.getId(), g.getName(), g.getSlug());
    }
}
