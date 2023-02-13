package com.comercios.cuentaComerciosBO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa una persona para el alta")
public class PersonaNuevaDTO {
    @Schema(name = "nombre", nullable = false, example = "Claudio")
    private String nombre;
    @Schema(name = "apellido", nullable = false, example = "Vilas")
    private String apellido;
    @Schema(name = "email", nullable = false, example = "claudio.vilas@eldars.com.ar")
    private String email;
    @Schema(name = "dni", nullable = false, example = "17405197")
    private String dni;
    @Schema(name = "telefono", nullable = true, example = "1167281038")
    private String telefono;
}
