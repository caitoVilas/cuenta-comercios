package com.comercios.cuentaComercios.mapper;

import com.comercios.cuentaComercios.dto.UsuarioDTO;
import com.comercios.cuentaComercios.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO usuarioToUsuarioDTO(Usuario request);
    List<UsuarioDTO> usuarioListToUsuarioDTOList(List<Usuario> request);
}
