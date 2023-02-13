package com.comercios.cuentaComerciosBO.mapper;

import com.comercios.cuentaComerciosBO.dto.DocumentacionDTO;
import com.comercios.cuentaComerciosBO.entity.Documentacion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentacionMapper {
    DocumentacionDTO documentacionToDocumentacionDTO(Documentacion request);
    List<DocumentacionDTO> documentacionListToDocumentacionDTOList(List<Documentacion> request);
}
