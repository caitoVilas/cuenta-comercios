package com.comercios.cuentaComerciosBO.controller;

import com.comercios.cuentaComerciosBO.dto.ComercioDTO;
import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.service.contract.ComercioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuenta-comercios-bo/comercios")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Cuenta Comercios BO - Comercios")
public class ComercioController {
    @Autowired
    private ComercioService comercioService;

    @GetMapping("/{id}")
    @Operation(description = "consulta comercio x id si existe", summary = "consulta comercio x id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<ComercioDTO> buscarComercio(@PathVariable Long id){
        ComercioDTO comercio = comercioService.buscarPorId(id);
        if (comercio == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(comercio);
    }

    @GetMapping
    @Operation(description = "consulta todos los ´comercios", summary = "consulta todos los comercios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<ComercioDTO>> buscarTodos(){
        List<ComercioDTO> comercios = comercioService.verTodos();
        if (comercios.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(comercios);
    }

    @GetMapping("/paginado")
    @Operation(description = "consulta todos los comercios paginado", summary = "consulta todos los comercios paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO<ComercioDTO>> buscarTodosPaginado(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        PageableResponseDTO<ComercioDTO> comercios = comercioService.verTodosPaginado(page, size);
        if (comercios.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(comercios);
    }
}
