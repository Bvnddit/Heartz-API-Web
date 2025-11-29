package com.heartz.heartz_api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.heartz.heartz_api.dto.LoginRequestDTO;
import com.heartz.heartz_api.model.Usuario;
import com.heartz.heartz_api.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> login(LoginRequestDTO request) {

        // Buscar usuario por email
        Usuario user = usuarioRepository.findByCorreo(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        // Validar contraseña
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        // Generar token
        String token = jwtService.getToken(user);

        // Preparar respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("idUsuario", user.getIdUsuario());
        response.put("token", token);
        response.put("email", user.getCorreo());
        response.put("rol", user.getRol().getNombre());

        return ResponseEntity.ok(response);
    }

}
