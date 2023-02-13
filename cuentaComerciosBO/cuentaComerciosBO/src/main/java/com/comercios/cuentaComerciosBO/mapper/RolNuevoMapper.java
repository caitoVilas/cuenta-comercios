package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.RolNuevoDTO;
import com.comercios.cuentaComerciosBO.entity.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolNuevoMapper {
    Rol rolNuevoDTOToRol(RolNuevoDTO request);
}
