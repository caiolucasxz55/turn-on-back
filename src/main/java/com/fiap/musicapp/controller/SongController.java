package com.fiap.musicapp.controller;

import com.fiap.musicapp.auth.AuthUser;
import com.fiap.musicapp.dto.response.SongResponse;
import com.fiap.musicapp.entity.User;
import com.fiap.musicapp.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping
    public List<SongResponse> findAll(@RequestParam(required = false) String genre) {
        return songService.findAll(genre);
    }

    @GetMapping("/recommended")
    public List<SongResponse> getRecommended(@AuthUser User user) {
        return songService.getRecommended(user);
    }

    @GetMapping("/{id}")
    public SongResponse findById(@PathVariable Long id) {
        return songService.findById(id);
    }
}
