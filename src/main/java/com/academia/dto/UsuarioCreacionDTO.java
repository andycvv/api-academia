package com.academia.dto;

import com.academia.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioCreacionDTO {
    private String username;
    private String password;
    private Boolean status;
    private List<Rol> roles;
}
