package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.PermisoNuevoDTO;
import com.comercios.cuentaComerciosBO.entity.Permiso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermisoNuevoMapper {
    Permiso permisoNuevoToPermiso(PermisoNuevoDTO request);
}
