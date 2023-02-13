package com.comercios.cuentaComerciosBO.service.contract;

import com.comercios.cuentaComerciosBO.dto.*;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioNuevoDTO dto);
    UsuarioDTO buscarPorId(Long id);
    List<UsuarioDTO> buscarTodos();
    PageableResponseDTO<UsuarioDTO> buscarTodosPaginado(int page, int size);
    void eliminarUsuario(Long id);
    LoginResponseDTO login(LoginDTO dto);
}
