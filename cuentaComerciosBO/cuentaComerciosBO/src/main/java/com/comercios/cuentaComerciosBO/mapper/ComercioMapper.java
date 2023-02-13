package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.ComercioDTO;
import com.comercios.cuentaComerciosBO.entity.Comercio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComercioMapper {
    ComercioDTO comercioToComercioDTO(Comercio request);
    List<ComercioDTO> comercioListToComercioDTOList(List<Comercio> request);

    Comercio comercioDTOToComercio(ComercioDTO request);
}
