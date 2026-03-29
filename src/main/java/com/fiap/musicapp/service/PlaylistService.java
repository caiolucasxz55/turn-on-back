package com.fiap.musicapp.service;

import com.fiap.musicapp.dto.request.PlaylistRequest;
import com.fiap.musicapp.dto.response.PlaylistResponse;
import com.fiap.musicapp.entity.Playlist;
import com.fiap.musicapp.entity.Song;
import com.fiap.musicapp.entity.User;
import com.fiap.musicapp.exception.ResourceNotFoundException;
import com.fiap.musicapp.repository.PlaylistRepository;
import com.fiap.musicapp.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;

    public List<PlaylistResponse> findAll(User user) {
        return playlistRepository.findByUser(user)
            .stream()
            .map(PlaylistResponse::from)
            .toList();
    }

    public PlaylistResponse findById(Long id, User user) {
        return PlaylistResponse.from(getOwnedPlaylist(id, user));
    }

    @Transactional
    public PlaylistResponse create(PlaylistRequest req, User user) {
        Playlist playlist = Playlist.builder()
            .name(req.name())
            .description(req.description())
            .user(user)
            .build();
        return PlaylistResponse.from(playlistRepository.save(playlist));
    }

    @Transactional
    public PlaylistResponse update(Long id, PlaylistRequest req, User user) {
        Playlist playlist = getOwnedPlaylist(id, user);
        playlist.setName(req.name());
        playlist.setDescription(req.description());
        return PlaylistResponse.from(playlistRepository.save(playlist));
    }

    @Transactional
    public void delete(Long id, User user) {
        Playlist playlist = getOwnedPlaylist(id, user);
        playlistRepository.delete(playlist);
    }

    @Transactional
    public PlaylistResponse addSong(Long playlistId, Long songId, User user) {
        Playlist playlist = getOwnedPlaylist(playlistId, user);
        Song song = songRepository.findById(songId)
            .orElseThrow(() -> new ResourceNotFoundException("Música não encontrada: " + songId));

        if (!playlist.getSongs().contains(song)) {
            playlist.getSongs().add(song);
            playlistRepository.save(playlist);
        }
        return PlaylistResponse.from(playlist);
    }

    @Transactional
    public PlaylistResponse removeSong(Long playlistId, Long songId, User user) {
        Playlist playlist = getOwnedPlaylist(playlistId, user);
        playlist.getSongs().removeIf(s -> s.getId().equals(songId));
        return PlaylistResponse.from(playlistRepository.save(playlist));
    }

    private Playlist getOwnedPlaylist(Long id, User user) {
        return playlistRepository.findByIdAndUser(id, user)
            .orElseThrow(() -> new ResourceNotFoundException("Playlist não encontrada: " + id));
    }
}
