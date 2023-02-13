package com.comercios.cuentaComerciosBO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un permiso para el alta")
public class PermisoNuevoDTO {
    @Schema(name = "descripcion", nullable = false, example = "aprobacion nivel 1")
    private String descripcion;
}
