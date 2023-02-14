package com.comercios.cuentaComercios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un comercio para su creacion")
public class ComercioNuevoDTO {
    @Schema(name = "razonSocial", required = true, example = "Bar de Manolo")
    private String razonSocial;
    @Schema(name = "domicilio", required = true, example = "Anchorena 700")
    private String domicilio;
    @Schema(name = "localidad", required = true, example = "Bahia Blanca")
    private String localidad;
    @Schema(name = "provincia", required = true, example = "Buenos Aires")
    private String provincia;
    @Schema(name = "email", required = true, example = "manolo@mail.com")
    private String email;
    @Schema(name = "cuit", required = true, example = "20112223337")
    private String cuit;
    @Schema(name = "categoria", required = true, example = "RI")
    private String categoria;
    @Schema(name = "telefono")
    private String telefono;
    @Schema(name = "usuarioId", required = true, example = "1")
    private Long usuarioId;
    private Integer sucursalRadicacion;
}
