package com.comercios.cuentaComerciosBO.service.contract;

import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.UsuarioAppDTO;

import java.util.List;

public interface UsuariosAppService {
    UsuarioAppDTO buscarPorId(Long id);
    List<UsuarioAppDTO> verTodos();
    PageableResponseDTO<UsuarioAppDTO> verTodosPaginado(int page, int size);
}
