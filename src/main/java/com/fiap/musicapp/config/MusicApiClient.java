package com.fiap.musicapp.config;

import com.fiap.musicapp.dto.response.ExternalSongResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;


@HttpExchange(url = "https://itunes.apple.com")
public interface MusicApiClient {

    @GetExchange("/search")
    ExternalSearchResponse search(
        @RequestParam String term,
        @RequestParam(defaultValue = "music") String media,
        @RequestParam(defaultValue = "10") int limit
    );
}
