package com.comercios.cuentaComercios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa una sucursal de radicacion para la respuesta")
public class SucursalDeRadicacionDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "codigoSucursal")
    private Integer codigoSucursal;
    @Schema(name = "nombre")
    private String nombre;
}
