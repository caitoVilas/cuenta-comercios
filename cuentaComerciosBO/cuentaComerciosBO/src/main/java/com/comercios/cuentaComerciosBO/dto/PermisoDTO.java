package com.comercios.cuentaComerciosBO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un permiso para salida")
public class PermisoDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "descripcion")
    private String descripcion;
}
