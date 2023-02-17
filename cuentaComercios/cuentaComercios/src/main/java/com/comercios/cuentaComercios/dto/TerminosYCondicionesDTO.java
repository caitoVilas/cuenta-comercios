package com.comercios.cuentaComercios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un terminos y condiciones para la respuesta")
public class TerminosYCondicionesDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "created")
    private LocalDateTime created;
    @Schema(name = "updated")
    private LocalDateTime updated;
    @Schema(name = "archivo")
    private String archivo;
    @Schema(name = "ruta")
    private String ruta;
    @Schema(name = "fechaVencimiento")
    private LocalDateTime fechaVencimiento;
}
