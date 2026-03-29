package com.fiap.musicapp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExternalSongResponse(
    String trackName,
    String artistName,
    String collectionName,
    String artworkUrl100,
    String previewUrl,
    Long trackTimeMillis
) {}
