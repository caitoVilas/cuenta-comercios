package com.comercios.cuentaComercios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un error para las respuestas")
public class ErrorDTO {
    private int codigo;
    private LocalDateTime timestamp;
    private String mensaje;
    private String ruta;
}
