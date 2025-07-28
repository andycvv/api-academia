package com.academia.service;

import com.academia.dto.RolCreacionDTO;
import com.academia.dto.RolDTO;
import reactor.core.publisher.Flux;

public interface IRolService extends IGenericService<RolDTO, RolCreacionDTO, String> {
}
