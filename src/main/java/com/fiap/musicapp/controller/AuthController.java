package com.fiap.musicapp.controller;

import com.fiap.musicapp.dto.request.LoginRequest;
import com.fiap.musicapp.dto.request.RegisterRequest;
import com.fiap.musicapp.dto.response.AuthResponse;
import com.fiap.musicapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@RequestBody @Valid RegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest req) {
        return authService.login(req);
    }
}
