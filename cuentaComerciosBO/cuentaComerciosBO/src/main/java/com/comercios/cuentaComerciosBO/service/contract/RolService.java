package com.comercios.cuentaComerciosBO.service.contract;

import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.RolDTO;
import com.comercios.cuentaComerciosBO.dto.RolNuevoDTO;
import com.comercios.cuentaComerciosBO.entity.Rol;

import java.util.List;

public interface RolService {
    RolDTO createRol(RolNuevoDTO dto);
    RolDTO buscarPorId(Long rolId);
    List<RolDTO> buscarTodos();
    PageableResponseDTO<RolDTO> buscarPaginado(int page, int size);
    void eliminarRol(Long rolId);
    Rol buscarPorNombre(String rolName);
}
