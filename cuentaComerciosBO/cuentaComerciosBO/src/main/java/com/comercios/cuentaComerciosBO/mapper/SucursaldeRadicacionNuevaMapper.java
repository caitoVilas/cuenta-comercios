package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.SucursalRadicacionNuevaDTO;
import com.comercios.cuentaComerciosBO.entity.SucursalDeRadicacion;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SucursaldeRadicacionNuevaMapper {
   SucursalDeRadicacion sucursalDeRadicacionNuevaToSucursaldeRadicacionDTO(SucursalRadicacionNuevaDTO request);
}
