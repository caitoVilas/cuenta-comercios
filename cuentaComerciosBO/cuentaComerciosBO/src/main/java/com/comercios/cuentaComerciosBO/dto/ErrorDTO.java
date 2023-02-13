package com.comercios.cuentaComerciosBO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(description = "modelo que representa un error para la salida")
public class ErrorDTO {
    private int codigo;
    private LocalDateTime timestamp;
    private String mensaje;
    private String ruta;
}
