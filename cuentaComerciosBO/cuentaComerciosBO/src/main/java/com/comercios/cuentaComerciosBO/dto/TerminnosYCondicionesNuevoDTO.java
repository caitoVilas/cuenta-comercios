package com.comercios.cuentaComerciosBO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un terminos y condiciones para su creacion")
public class TerminnosYCondicionesNuevoDTO {
    @Schema(name = "fechaVencimiento")
    private String fechaVencimiento;
}
