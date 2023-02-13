package com.comercios.cuentaComerciosBO.dto;

import com.comercios.cuentaComerciosBO.enums.DocumentacionTipo;
import com.comercios.cuentaComerciosBO.enums.EstadoDocumentacion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa una documentacion para la respuesta")
public class DocumentacionDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "documentacionTipo")
    private DocumentacionTipo documentacionTipo;
    @Schema(name = "archivo")
    private String archivo;
    @Schema(name = "ruta")
    private String ruta;
    @Schema(name = "estadoDocumentacion")
    private EstadoDocumentacion estado;
    @Schema(name = "comercio")
    private ComercioDTO comercio;
}
