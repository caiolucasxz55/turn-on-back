package com.fiap.musicapp.auth;

import com.fiap.musicapp.entity.User;
import com.fiap.musicapp.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final Set<String> PUBLIC_PATHS = Set.of(
        "/auth/login",
        "/auth/register",
        "/genres"
    );

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws ServletException, IOException {

        String path = request.getRequestURI();

        // Rotas públicas passam direto
        if (PUBLIC_PATHS.contains(path)) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            sendUnauthorized(response, "Token não informado");
            return;
        }

        String token = header.substring(7);

        if (!jwtService.isValid(token)) {
            sendUnauthorized(response, "Token inválido ou expirado");
            return;
        }

        String email = jwtService.extractEmail(token);
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            sendUnauthorized(response, "Usuário não encontrado");
            return;
        }

        // Injeta o usuário no request para uso nos controllers
        request.setAttribute("authenticatedUser", user);
        chain.doFilter(request, response);
    }

    private void sendUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"" + message + "\"}");
    }
}
