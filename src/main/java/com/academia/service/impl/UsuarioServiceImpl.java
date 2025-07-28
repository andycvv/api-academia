package com.academia.service.impl;

import com.academia.dto.UsuarioCreacionDTO;
import com.academia.dto.UsuarioDTO;
import com.academia.mapper.GenericMapper;
import com.academia.mapper.UsuarioMapper;
import com.academia.model.Rol;
import com.academia.model.Usuario;
import com.academia.repository.IGenericRepository;
import com.academia.repository.IRolRepository;
import com.academia.repository.IUsuarioRepository;
import com.academia.security.UsuarioSecurity;
import com.academia.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends GenericServiceImpl<
        Usuario,
        UsuarioDTO,
        UsuarioCreacionDTO,
        String> implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IRolRepository rolRepository;
    private final UsuarioMapper mapper;

    @Override
    protected IGenericRepository<Usuario, String> repo() {
        return usuarioRepository;
    }

    @Override
    protected GenericMapper<Usuario, UsuarioDTO, UsuarioCreacionDTO, String> mapper() {
        return mapper;
    }

    @Override
    public Mono<UsuarioSecurity> findOneByUsername(String username) {
        return usuarioRepository.findOneByUsername(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException(
                        "Usuario no encontrado con el username: " + username)))
                .zipWhen(user -> Flux.fromIterable(user.getRoles())
                        .flatMap(rol -> rolRepository.findById(rol.getId()).map(Rol::getName))
                        .collectList()
                )
                .map(tuple -> {
                    Usuario user = tuple.getT1();
                    List<String> roles = tuple.getT2();

                    return new UsuarioSecurity(
                            user.getUsername(),
                            user.getPassword(),
                            user.getStatus(),
                            roles
                    );
                });
    }
}
