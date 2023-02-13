package com.comercios.cuentaComercios.mapper;

import com.comercios.cuentaComercios.dto.UsuarioNuevoDTO;
import com.comercios.cuentaComercios.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsarioNuevoMapper {
    Usuario usuarioNuevoDTOToUsario(UsuarioNuevoDTO request);
}
