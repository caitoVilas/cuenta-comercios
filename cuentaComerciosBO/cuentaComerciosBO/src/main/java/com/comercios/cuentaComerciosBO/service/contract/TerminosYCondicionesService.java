package com.comercios.cuentaComerciosBO.service.contract;

import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.TerminnosYCondicionesNuevoDTO;
import com.comercios.cuentaComerciosBO.dto.TerminosYCondicionesDTO;
import org.springframework.web.multipart.MultipartFile;

public interface TerminosYCondicionesService {
    TerminosYCondicionesDTO guardarTyc(String fechaVencimiento, MultipartFile file);
    TerminosYCondicionesDTO buscarPorId(Long id);
    PageableResponseDTO<TerminosYCondicionesDTO> verTodosPaginado(int page, int size);
}
