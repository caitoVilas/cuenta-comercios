package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.PersonaNuevaDTO;
import com.comercios.cuentaComerciosBO.entity.Persona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaNuevaMapper {
    Persona personaNuevaDTOToPersona(PersonaNuevaDTO request);
}
