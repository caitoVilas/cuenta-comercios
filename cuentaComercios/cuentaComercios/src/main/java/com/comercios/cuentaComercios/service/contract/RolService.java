package com.comercios.cuentaComercios.service.contract;

import com.comercios.cuentaComercios.dto.RolDTO;
import com.comercios.cuentaComercios.entity.Rol;

import java.util.List;

public interface RolService {
    Rol buscarPorNombre(String roleName);
    List<RolDTO> verTodos();
}
