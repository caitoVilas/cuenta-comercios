package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.TerminosYCondicionesDTO;
import com.comercios.cuentaComerciosBO.entity.TerminosYCondiciones;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TerminosYCondicionesMapper {
    TerminosYCondicionesDTO tycToTycDTO(TerminosYCondiciones request);
    List<TerminosYCondicionesDTO> tycListToTycDTOList(List<TerminosYCondiciones> request);
}
