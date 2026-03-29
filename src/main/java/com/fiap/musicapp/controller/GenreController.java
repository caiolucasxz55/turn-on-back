package com.fiap.musicapp.controller;

import com.fiap.musicapp.dto.response.GenreResponse;
import com.fiap.musicapp.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreResponse> findAll() {
        return genreService.findAll();
    }
}
