package com.comercios.cuentaComercios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un usuario para el registro")
public class UsuarioNuevoDTO {
    @Schema(name = "username", nullable = false, example = "caito")
    private String username;
    @Schema(name = "password", nullable = false, example = "miPass123456")
    private String password;
    private PersonaNuevaDTO persona;
    private List<RolNuevoDTO> roles;
}
