package com.comercios.cuentaComerciosBO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa una sucursal de racicacion para su creacion")
public class SucursalRadicacionNuevaDTO {
    @Schema(name = "codigoSucursal", required = true, example = "1000")
    private Integer codigoSucursal;
    @Schema(name = "nombre", required = true, example = "Casa Central")
    private String nombre;
}
