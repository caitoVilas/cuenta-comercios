package com.comercios.cuentaComercios.service.contract;

import com.comercios.cuentaComercios.dto.ComercioDTO;
import com.comercios.cuentaComercios.dto.ComercioIdDTO;
import com.comercios.cuentaComercios.dto.ComercioNuevoDTO;
import com.comercios.cuentaComercios.dto.PageableResponseDTO;
import com.comercios.cuentaComercios.enums.DocumentacionTipo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface ComercioService {
    ComercioDTO crearComercio(ComercioNuevoDTO dto);
    ComercioDTO buscarPorId(Long id);
    List<ComercioDTO> verTodos();
    PageableResponseDTO<ComercioDTO> verTodosPaginado(int page, int size);
    void guardarDocumento(MultipartFile file, Long comercioId, DocumentacionTipo dt);
    ComercioDTO aceptarTyc(Long comercioId, Long tycId);
}
