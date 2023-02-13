package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.PersonaDTO;
import com.comercios.cuentaComerciosBO.entity.Persona;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaDTO personaToPersonaDTO(Persona request);
    List<PersonaDTO> personaListToPersonaDTOList(List<Persona> request);

    Persona personaDTOToPersona(PersonaDTO persona);
}
