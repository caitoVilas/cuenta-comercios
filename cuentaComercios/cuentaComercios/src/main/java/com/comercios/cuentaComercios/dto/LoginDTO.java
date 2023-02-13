package com.comercios.cuentaComercios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa el login de un usuario")
public class LoginDTO {
    @Schema(name = "username", required = true, example = "caito")
    private String username;
    @Schema(name = "password", required = true, example = "miClave1234")
    private String password;
}
