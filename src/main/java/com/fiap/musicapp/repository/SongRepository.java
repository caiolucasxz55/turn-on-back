package com.fiap.musicapp.repository;

import com.fiap.musicapp.entity.Genre;
import com.fiap.musicapp.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByGenre(Genre genre);
    List<Song> findByGenreIn(Set<Genre> genres);
    List<Song> findByGenre_Slug(String slug);
}
