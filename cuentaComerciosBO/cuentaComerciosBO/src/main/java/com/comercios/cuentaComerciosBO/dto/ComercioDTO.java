package com.comercios.cuentaComerciosBO.dto;

import com.comercios.cuentaComerciosBO.enums.EstadoComercio;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un comercio para las respuestas")
public class ComercioDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "razonSocial")
    private String razonSocial;
    @Schema(name = "domicilio")
    private String domicilio;
    @Schema(name = "localidad")
    private String localidad;
    @Schema(name = "provincia")
    private String provincia;
    @Schema(name = "email")
    private String email;
    @Schema(name = "cuit")
    private String cuit;
    @Schema(name = "categoria")
    private String categoria;
    @Schema(name = "telefono")
    private String telefono;
    @Schema(name = "limite")
    private boolean limite;
    @Schema(name = "estado")
    private EstadoComercio estado;
    @Schema(name = "created")
    private LocalDateTime created;
    @Schema(name = "updated")
    private LocalDateTime updated;
    @Schema(name = "usuario")
    private UsuarioAppDTO usuario;
    @Schema(name = "sucursalDeRadicacion")
    private SucursalRadicacionDTO sucursalDeRadicacion;
}
