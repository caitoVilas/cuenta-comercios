package com.comercios.cuentaComercios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un usario para respuesta")
public class UsuarioDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "username", nullable = false, example = "caito")
    private String username;
    private PersonaDTO persona;
    private List<RolDTO> roles;
}
