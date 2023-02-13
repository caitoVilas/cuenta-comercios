package com.comercios.cuentaComerciosBO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un usuario de la app")
public class UsuarioAppDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "username")
    private String username;
    @Schema(name = "created")
    private LocalDateTime created;
    @Schema(name = "updated")
    private LocalDateTime updated;
    @Schema(name = "persona")
    private PersonaDTO persona;
    @Schema(name = "roles")
    private List<RolDTO> roles;
}
