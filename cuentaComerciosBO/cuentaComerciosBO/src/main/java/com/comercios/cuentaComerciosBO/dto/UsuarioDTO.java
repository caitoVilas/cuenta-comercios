package com.comercios.cuentaComerciosBO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "modelo que representa un usuario para la salida")
public class UsuarioDTO {
    private Long id;
    private String username;
    private PersonaDTO persona;
    private List<RolDTO> roles;
    private SucursalRadicacionDTO sucursalDeRadicacion;
}
