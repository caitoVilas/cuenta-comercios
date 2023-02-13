package com.comercios.cuentaComercios.service.contract;

import com.comercios.cuentaComercios.dto.*;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO crearusuario(UsuarioNuevoDTO dto);
    UsuarioDTO buscarrPorid(Long id);
    List<UsuarioDTO> verTodos();
    PageableResponseDTO<UsuarioDTO> verTodosPaginado(int page, int size);
    void eliminarUsuario(Long id);
    LoginResponseDTO login(LoginDTO dto);
}
