package com.academia.service.impl;

import com.academia.dto.RolCreacionDTO;
import com.academia.dto.RolDTO;
import com.academia.mapper.RolMapper;
import com.academia.mapper.GenericMapper;
import com.academia.model.Rol;
import com.academia.repository.IRolRepository;
import com.academia.repository.IGenericRepository;
import com.academia.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolServiceImpl extends GenericServiceImpl<
        Rol,
        RolDTO,
        RolCreacionDTO,
        String> implements IRolService {

    private final IRolRepository repo;
    private final RolMapper mapper;

    @Override
    protected IGenericRepository<Rol, String> repo() {
        return repo;
    }

    @Override
    protected GenericMapper<Rol, RolDTO, RolCreacionDTO, String> mapper() {
        return mapper;
    }
}
