package com.comercios.cuentaComerciosBO.service.contract;

import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.PermisoDTO;
import com.comercios.cuentaComerciosBO.dto.PermisoNuevoDTO;

import java.util.List;

public interface PermisoService {
    PermisoDTO crearPermiso(PermisoNuevoDTO dto);
    PermisoDTO buscarXId(Long id);
    List<PermisoDTO> buscarTodos();
    PageableResponseDTO<PermisoDTO> buscarTodosPaginado(int page, int size);
    void eliminarPermiso(Long id);
}
