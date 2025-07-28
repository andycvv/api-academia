package com.academia.service;

import com.academia.dto.UsuarioCreacionDTO;
import com.academia.dto.UsuarioDTO;
import com.academia.model.Usuario;
import com.academia.security.UsuarioSecurity;
import reactor.core.publisher.Mono;

public interface IUsuarioService extends IGenericService<UsuarioDTO, UsuarioCreacionDTO, String> {
    Mono<UsuarioSecurity> findOneByUsername(String username);
}
