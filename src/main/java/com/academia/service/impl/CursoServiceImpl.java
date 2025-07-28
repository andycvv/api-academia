package com.academia.service.impl;

import com.academia.dto.CursoCreacionDTO;
import com.academia.dto.CursoDTO;
import com.academia.mapper.CursoMapper;
import com.academia.mapper.GenericMapper;
import com.academia.model.Curso;
import com.academia.repository.ICursoRepository;
import com.academia.repository.IGenericRepository;
import com.academia.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends GenericServiceImpl<
        Curso,
        CursoDTO,
        CursoCreacionDTO,
        String> implements ICursoService {

    private final ICursoRepository repo;
    private final CursoMapper mapper;

    @Override
    protected IGenericRepository<Curso, String> repo() {
        return repo;
    }

    @Override
    protected GenericMapper<Curso, CursoDTO, CursoCreacionDTO, String> mapper() {
        return mapper;
    }
}
