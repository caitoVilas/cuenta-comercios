package com.comercios.cuentaComercios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo qu representa la respuesta de un login")
public class LoginResponseDTO {
    private String token;
    private UsuarioDTO usuario;
}
