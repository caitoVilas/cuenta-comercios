package com.comercios.cuentaComerciosBO.service.contract;

import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.SucursalRadicacionDTO;
import com.comercios.cuentaComerciosBO.dto.SucursalRadicacionNuevaDTO;

import java.util.List;

public interface SucursalDeRadicacionService {
    SucursalRadicacionDTO createSucursal(SucursalRadicacionNuevaDTO dto);
    SucursalRadicacionDTO buscarPorCodigo(Integer codigoSucursal);
    List<SucursalRadicacionDTO> verTodos();
    PageableResponseDTO<SucursalRadicacionDTO> verTodoPaginado(int page, int size);
}
