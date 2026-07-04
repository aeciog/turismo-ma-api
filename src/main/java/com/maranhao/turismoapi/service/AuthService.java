package com.maranhao.turismoapi.service;

import com.maranhao.turismoapi.dto.AuthResponse;
import com.maranhao.turismoapi.dto.LoginRequest;
import com.maranhao.turismoapi.dto.RegisterRequest;
import com.maranhao.turismoapi.model.Role;
import com.maranhao.turismoapi.model.Usuario;
import com.maranhao.turismoapi.repository.UsuarioRepository;
import com.maranhao.turismoapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .role(Role.USER)
                .build();

        repository.save(usuario);
        String token = jwtService.gerarToken(usuario.getEmail());
        return new AuthResponse(token, usuario.getEmail(), usuario.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Credenciais inválidas"));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        String token = jwtService.gerarToken(usuario.getEmail());
        return new AuthResponse(token, usuario.getEmail(), usuario.getRole().name());
    }
}