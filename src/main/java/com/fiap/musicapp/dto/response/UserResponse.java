package com.fiap.musicapp.dto.response;

import com.fiap.musicapp.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UserResponse(Long id, String name, String email, Set<GenreResponse> favoriteGenres) {
    public static UserResponse from(User u) {
        return new UserResponse(
            u.getId(),
            u.getName(),
            u.getEmail(),
            u.getFavoriteGenres().stream().map(GenreResponse::from).collect(Collectors.toSet())
        );
    }
}
