package com.fiap.musicapp.controller;

import com.fiap.musicapp.config.ExternalSearchResponse;
import com.fiap.musicapp.config.MusicApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external/songs")
@RequiredArgsConstructor
public class ExternalMusicController {

    private final MusicApiClient musicApiClient;

    /**
     * Busca músicas na API pública do iTunes.
     * Exemplo: GET /external/songs/search?term=Beatles&limit=5
     */
    @GetMapping("/search")
    public ExternalSearchResponse search(
        @RequestParam String term,
        @RequestParam(defaultValue = "10") int limit
    ) {
        return musicApiClient.search(term, "music", limit);
    }
}
