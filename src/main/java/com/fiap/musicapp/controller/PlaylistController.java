package com.fiap.musicapp.controller;

import com.fiap.musicapp.auth.AuthUser;
import com.fiap.musicapp.dto.request.PlaylistRequest;
import com.fiap.musicapp.dto.response.PlaylistResponse;
import com.fiap.musicapp.entity.User;
import com.fiap.musicapp.service.PlaylistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public List<PlaylistResponse> findAll(@AuthUser User user) {
        return playlistService.findAll(user);
    }

    @GetMapping("/{id}")
    public PlaylistResponse findById(@PathVariable Long id, @AuthUser User user) {
        return playlistService.findById(id, user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistResponse create(@RequestBody @Valid PlaylistRequest req, @AuthUser User user) {
        return playlistService.create(req, user);
    }

    @PutMapping("/{id}")
    public PlaylistResponse update(
        @PathVariable Long id,
        @RequestBody @Valid PlaylistRequest req,
        @AuthUser User user
    ) {
        return playlistService.update(id, req, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @AuthUser User user) {
        playlistService.delete(id, user);
    }

    @PostMapping("/{id}/songs/{songId}")
    public PlaylistResponse addSong(
        @PathVariable Long id,
        @PathVariable Long songId,
        @AuthUser User user
    ) {
        return playlistService.addSong(id, songId, user);
    }

    @DeleteMapping("/{id}/songs/{songId}")
    public PlaylistResponse removeSong(
        @PathVariable Long id,
        @PathVariable Long songId,
        @AuthUser User user
    ) {
        return playlistService.removeSong(id, songId, user);
    }
}
