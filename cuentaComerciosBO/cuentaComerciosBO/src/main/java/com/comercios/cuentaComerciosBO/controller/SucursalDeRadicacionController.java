package com.comercios.cuentaComerciosBO.controller;

import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.SucursalRadicacionDTO;
import com.comercios.cuentaComerciosBO.dto.SucursalRadicacionNuevaDTO;
import com.comercios.cuentaComerciosBO.service.contract.SucursalDeRadicacionService;
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
@RequestMapping("/api/v1/cuenta-comercios-bo/sucursal-radicacion")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Cuenta Comercios BO - Sucursal de Radicacion")
public class SucursalDeRadicacionController {
    @Autowired
    private SucursalDeRadicacionService sucursalDeRadicacionService;

    @PostMapping
    @Operation(description = "creacion de sucursal de radicacion", summary = "creacion de sucursal de radicacion")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<SucursalRadicacionDTO> crearSucursal(@RequestBody SucursalRadicacionNuevaDTO dto){
        return ResponseEntity.ok(sucursalDeRadicacionService.createSucursal(dto));
    }

    @GetMapping("/{codigoSucursal}")
    @Operation(description = "consulta sucursal de radicacion x codigo si existe",
            summary = "consulta sucursal de radicacion x codigo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<SucursalRadicacionDTO> buscarPorCodigo(@PathVariable Integer codigoSucursal){
        SucursalRadicacionDTO sucursal = sucursalDeRadicacionService.buscarPorCodigo(codigoSucursal);
        if (sucursal == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sucursal);
    }

    @GetMapping
    @Operation(description = "consulta todos las sucursales de radicacion",
            summary = "consulta todas las sucursales de radicacion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<SucursalRadicacionDTO>> buscarTodas(){
        List<SucursalRadicacionDTO> sucursales = sucursalDeRadicacionService.verTodos();
        if (sucursales.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(sucursales);
    }

    @GetMapping("/paginado")
    @Operation(description = "consulta todos las sucursales de radicacion paginado",
            summary = "consulta todas las sucursales de radicacion paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO<SucursalRadicacionDTO>> buscarTodosPaginado(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        PageableResponseDTO<SucursalRadicacionDTO> sucursales =
                sucursalDeRadicacionService.verTodoPaginado(page, size);
        if (sucursales.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(sucursales);
    }
}
