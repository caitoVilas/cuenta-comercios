package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.UsuarioDTO;
import com.comercios.cuentaComerciosBO.entity.UsuarioBO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO usuarioToUsuarioDTO(UsuarioBO request);
    List<UsuarioDTO> usuarioListToUsarioDTOList(List<UsuarioBO> request);
}
