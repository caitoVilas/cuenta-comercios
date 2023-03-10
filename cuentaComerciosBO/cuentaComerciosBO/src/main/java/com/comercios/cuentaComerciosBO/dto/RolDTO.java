package com.comercios.cuentaComerciosBO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un rol para la salida")
public class RolDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "rolName")
    private String rolName;
    @Schema(name = "permisos")
    private List<PermisoDTO> permisos;
}
