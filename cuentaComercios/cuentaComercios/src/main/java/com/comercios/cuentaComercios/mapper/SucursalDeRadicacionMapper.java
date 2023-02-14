package com.comercios.cuentaComercios.mapper;

import com.comercios.cuentaComercios.dto.SucursalDeRadicacionDTO;
import com.comercios.cuentaComercios.entity.SucursalDeRadicacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SucursalDeRadicacionMapper {
    SucursalDeRadicacionDTO sucursalDeRadicacionToSucursalDeRadiconDTO(SucursalDeRadicacion request);
}
