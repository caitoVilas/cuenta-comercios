package com.comercios.cuentaComerciosBO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un rol para el alta")
public class RolNuevoDTO {
    @Schema(name = "rolName", nullable = false, example = "ROLE_ADMIN")
    private String rolName;
}
