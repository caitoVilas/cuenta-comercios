package com.comercios.cuentaComercios.mapper;

import com.comercios.cuentaComercios.dto.TerminosYCondicionesDTO;
import com.comercios.cuentaComercios.entity.TerminosYCondiciones;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TerminosYCondicionesMapper {
    TerminosYCondicionesDTO tycToTycDTO(TerminosYCondiciones request);
    List<TerminosYCondicionesDTO> tycListToTycDTOList(List<TerminosYCondiciones> request);
}
