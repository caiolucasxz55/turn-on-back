package com.fiap.musicapp.dto.response;

import com.fiap.musicapp.entity.Song;

public record SongResponse(
    Long id,
    String title,
    String artist,
    String album,
    Integer duration,
    String coverUrl,
    String previewUrl,
    GenreResponse genre
) {
    public static SongResponse from(Song s) {
        return new SongResponse(
            s.getId(),
            s.getTitle(),
            s.getArtist(),
            s.getAlbum(),
            s.getDuration(),
            s.getCoverUrl(),
            s.getPreviewUrl(),
            GenreResponse.from(s.getGenre())
        );
    }
}
