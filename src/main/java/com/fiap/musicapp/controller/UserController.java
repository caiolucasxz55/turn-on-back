package com.fiap.musicapp.controller;

import com.fiap.musicapp.auth.AuthUser;
import com.fiap.musicapp.dto.request.UpdateFavoriteGenresRequest;
import com.fiap.musicapp.dto.request.UpdateUserRequest;
import com.fiap.musicapp.dto.response.UserResponse;
import com.fiap.musicapp.entity.User;
import com.fiap.musicapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getProfile(@AuthUser User user) {
        return userService.getProfile(user);
    }

    @PutMapping("/me")
    public UserResponse updateProfile(
        @AuthUser User user,
        @RequestBody @Valid UpdateUserRequest req
    ) {
        return userService.updateProfile(user, req);
    }

    @PutMapping("/me/genres")
    public UserResponse updateFavoriteGenres(
        @AuthUser User user,
        @RequestBody @Valid UpdateFavoriteGenresRequest req
    ) {
        return userService.updateFavoriteGenres(user, req);
    }
}
