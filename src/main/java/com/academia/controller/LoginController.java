package com.academia.controller;

import com.academia.model.Rol;
import com.academia.security.AuthRequest;
import com.academia.security.AuthResponse;
import com.academia.security.JwtUtil;
import com.academia.security.UsuarioSecurity;
import com.academia.service.IUsuarioService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.management.relation.Role;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
    private final JwtUtil jwtUtil;
    private final IUsuarioService service;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest request) {
        return service.findOneByUsername(request.getUsername())
                .map(userDetails -> {
                    if (BCrypt.checkpw(request.getPassword(), userDetails.getPassword())) {
                        String token =jwtUtil.generateToken(userDetails);
                        return ResponseEntity.ok(new AuthResponse(token));
                    }

                    return ResponseEntity.badRequest().build();
                });
    }

}
