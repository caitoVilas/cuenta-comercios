package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.PermisoDTO;
import com.comercios.cuentaComerciosBO.entity.Permiso;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermisoMapper {
    PermisoDTO permisoToPermisoDTO(Permiso request);
    List<PermisoDTO> permisoListToPermisoDTOList(List<Permiso> request);
}
