package com.comercios.cuentaComercios.dto;

import com.comercios.cuentaComercios.enums.EstadoComercio;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un comercio para la respuesta")
public class ComercioDTO {
    @Schema(name = "id")
    private Long id;
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
    @Schema(name = "telefono", required = true, example = "42090815")
    private String telefono;
    @Schema(name = "limite")
    private boolean limite;
    @Schema(name = "estado")
    private EstadoComercio estado;
    @Schema(name = "usuario", required = true, example = "1")
    private UsuarioDTO usuario;
    @Schema(name = "sucursalDeRadicacion")
    private SucursalDeRadicacionDTO sucursalDeRadicacion;
    @Schema(name = "terminosYCondiciones")
    private TerminosYCondicionesDTO terminosYCondiciones;
}
