package com.comercios.cuentaComerciosBO.dto;

import com.comercios.cuentaComerciosBO.entity.Rol;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un usuario para el alta")
public class UsuarioNuevoDTO {
    @Schema(name = "username", nullable = false, example = "caito")
    private String username;
    @Schema(name = "password", nullable = false, example = "miPass123456")
    private String password;
    private PersonaDTO persona;
    private List<RolNuevoDTO> roles;
}
