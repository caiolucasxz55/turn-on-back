package com.fiap.musicapp.service;

import com.fiap.musicapp.dto.response.SongResponse;
import com.fiap.musicapp.entity.Song;
import com.fiap.musicapp.entity.User;
import com.fiap.musicapp.exception.ResourceNotFoundException;
import com.fiap.musicapp.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public List<SongResponse> findAll(String genreSlug) {
        List<Song> songs = (genreSlug != null && !genreSlug.isBlank())
            ? songRepository.findByGenre_Slug(genreSlug)
            : songRepository.findAll();

        return songs.stream().map(SongResponse::from).toList();
    }

    public SongResponse findById(Long id) {
        Song song = songRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Música não encontrada: " + id));
        return SongResponse.from(song);
    }

    public List<SongResponse> getRecommended(User user) {
        if (user.getFavoriteGenres().isEmpty()) {
            return songRepository.findAll().stream().limit(10).map(SongResponse::from).toList();
        }
        return songRepository.findByGenreIn(user.getFavoriteGenres())
            .stream()
            .map(SongResponse::from)
            .toList();
    }
}
