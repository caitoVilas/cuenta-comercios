package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.SucursalRadicacionDTO;
import com.comercios.cuentaComerciosBO.entity.SucursalDeRadicacion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SucursalDeRadicacionMapper {
    SucursalRadicacionDTO sucursalDeRadicacionToSucursalDeRadicionDTO(SucursalDeRadicacion request);
    List<SucursalRadicacionDTO> sucursalDeRadicacionListToSucursalDeRadicacionDTOList(
            List<SucursalDeRadicacion> request);
}
