package com.fiap.musicapp.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fiap.musicapp.auth.JwtService;
import com.fiap.musicapp.dto.request.LoginRequest;
import com.fiap.musicapp.dto.request.RegisterRequest;
import com.fiap.musicapp.dto.response.AuthResponse;
import com.fiap.musicapp.dto.response.UserResponse;
import com.fiap.musicapp.entity.User;
import com.fiap.musicapp.exception.EmailAlreadyExistsException;
import com.fiap.musicapp.exception.InvalidCredentialsException;
import com.fiap.musicapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            throw new EmailAlreadyExistsException("E-mail já cadastrado: " + req.email());
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, req.password().toCharArray());

        User user = User.builder()
            .name(req.name())
            .email(req.email())
            .password(hashedPassword)
            .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, UserResponse.from(user));
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.email())
            .orElseThrow(() -> new InvalidCredentialsException("Credenciais inválidas"));

        BCrypt.Result result = BCrypt.verifyer().verify(req.password().toCharArray(), user.getPassword());
        if (!result.verified) {
            throw new InvalidCredentialsException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, UserResponse.from(user));
    }
}
