package com.comercios.cuentaComercios.mapper;

import com.comercios.cuentaComercios.dto.RolNuevoDTO;
import com.comercios.cuentaComercios.entity.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolNuevoMapper {
    Rol rolNuevoDTOToRol(RolNuevoDTO request);
}
