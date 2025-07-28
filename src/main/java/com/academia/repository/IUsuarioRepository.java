package com.academia.repository;

import com.academia.model.Usuario;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IUsuarioRepository extends IGenericRepository<Usuario, String> {
    Mono<Usuario> findOneByUsername(String username);
}
