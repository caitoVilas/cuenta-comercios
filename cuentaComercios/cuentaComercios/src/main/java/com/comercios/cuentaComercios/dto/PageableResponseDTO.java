package com.comercios.cuentaComercios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que represta una respuesta paginada")
public class PageableResponseDTO<I> {
    private List<I> content;
    private int pagina;
    private Long resultados;
    private int totalPaginas;
}
