package com.comercios.cuentaComercios.mapper;

import com.comercios.cuentaComercios.dto.PersonaDTO;
import com.comercios.cuentaComercios.dto.PersonaNuevaDTO;
import com.comercios.cuentaComercios.entity.Persona;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaDTO personaToPersonaDTO(Persona request);
    List<PersonaDTO> personaListToPersonaDTOList(List<Persona> request);

    Persona personaDTOToPersona(PersonaNuevaDTO request);
}
