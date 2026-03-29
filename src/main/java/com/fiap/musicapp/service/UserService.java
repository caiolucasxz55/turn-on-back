package com.fiap.musicapp.service;

import com.fiap.musicapp.dto.request.UpdateFavoriteGenresRequest;
import com.fiap.musicapp.dto.request.UpdateUserRequest;
import com.fiap.musicapp.dto.response.UserResponse;
import com.fiap.musicapp.entity.Genre;
import com.fiap.musicapp.entity.User;
import com.fiap.musicapp.exception.ResourceNotFoundException;
import com.fiap.musicapp.repository.GenreRepository;
import com.fiap.musicapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GenreRepository genreRepository;

    public UserResponse getProfile(User user) {
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse updateProfile(User user, UpdateUserRequest req) {
        user.setName(req.name());
        userRepository.save(user);
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse updateFavoriteGenres(User user, UpdateFavoriteGenresRequest req) {
        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(req.genreIds()));
        if (genres.size() != req.genreIds().size()) {
            throw new ResourceNotFoundException("Um ou mais gêneros não foram encontrados");
        }
        user.setFavoriteGenres(genres);
        userRepository.save(user);
        return UserResponse.from(user);
    }
}
