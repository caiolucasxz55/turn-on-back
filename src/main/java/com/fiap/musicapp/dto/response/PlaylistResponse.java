package com.fiap.musicapp.dto.response;

import com.fiap.musicapp.entity.Playlist;

import java.util.List;

public record PlaylistResponse(
    Long id,
    String name,
    String description,
    List<SongResponse> songs
) {
    public static PlaylistResponse from(Playlist p) {
        return new PlaylistResponse(
            p.getId(),
            p.getName(),
            p.getDescription(),
            p.getSongs().stream().map(SongResponse::from).toList()
        );
    }
}
