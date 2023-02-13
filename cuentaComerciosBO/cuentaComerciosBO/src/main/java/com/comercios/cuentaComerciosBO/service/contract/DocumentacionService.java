package com.comercios.cuentaComerciosBO.service.contract;

import com.comercios.cuentaComerciosBO.dto.DocumentacionDTO;

import java.util.List;

public interface DocumentacionService {
    List<DocumentacionDTO> buscarPorComercio(Long comercioId);
}
