package com.fiap.musicapp.service;

import com.fiap.musicapp.dto.response.GenreResponse;
import com.fiap.musicapp.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<GenreResponse> findAll() {
        return genreRepository.findAll()
            .stream()
            .map(GenreResponse::from)
            .toList();
    }
}
