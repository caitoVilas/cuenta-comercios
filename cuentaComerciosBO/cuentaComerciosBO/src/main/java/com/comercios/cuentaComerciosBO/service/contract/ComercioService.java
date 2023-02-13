package com.comercios.cuentaComerciosBO.service.contract;

import com.comercios.cuentaComerciosBO.dto.ComercioDTO;
import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;

import java.util.List;

public interface ComercioService {
    ComercioDTO buscarPorId(Long id);
    List<ComercioDTO> verTodos();
    PageableResponseDTO<ComercioDTO> verTodosPaginado(int page, int size);
}
