package com.comercios.cuentaComercios.mapper;

import com.comercios.cuentaComercios.dto.PersonaNuevaDTO;
import com.comercios.cuentaComercios.entity.Persona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaNuevaMapper {
    Persona personaNuevaDTOToPersona(PersonaNuevaDTO request);
}
