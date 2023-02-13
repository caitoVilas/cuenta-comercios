package com.comercios.cuentaComercios.mapper;

import com.comercios.cuentaComercios.dto.ComercioDTO;
import com.comercios.cuentaComercios.entity.Comercio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComercioMapper {
    ComercioDTO comercioToComercioDTO(Comercio request);
    List<ComercioDTO> comercioListToComercioDTOList(List<Comercio> request);
}
