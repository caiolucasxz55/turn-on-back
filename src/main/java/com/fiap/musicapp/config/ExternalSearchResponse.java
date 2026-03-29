package com.fiap.musicapp.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.musicapp.dto.response.ExternalSongResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExternalSearchResponse(
    int resultCount,
    List<ExternalSongResponse> results
) {}
