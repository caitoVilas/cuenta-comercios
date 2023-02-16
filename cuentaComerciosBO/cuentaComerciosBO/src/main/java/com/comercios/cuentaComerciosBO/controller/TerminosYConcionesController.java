package com.comercios.cuentaComerciosBO.controller;

import com.comercios.cuentaComerciosBO.dto.ComercioDTO;
import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.TerminnosYCondicionesNuevoDTO;
import com.comercios.cuentaComerciosBO.dto.TerminosYCondicionesDTO;
import com.comercios.cuentaComerciosBO.service.contract.TerminosYCondicionesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/cuenta-comercios-bo/terminos-y-condiciones")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Cuenta Comercios BO - Terminos y Condiciones")
public class TerminosYConcionesController {
    @Autowired
    private TerminosYCondicionesService terminosYCondicionesService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces =  {MediaType.APPLICATION_JSON_VALUE},
            path = "/{fechaVencimiento}")
    @Operation(description = "carga de terminos y condiciones", summary = "carga de terminos y condiciones")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<?> subirDocumento(@RequestPart("file") MultipartFile file,
                                            @PathVariable String fechaVencimiento){
        terminosYCondicionesService.guardarTyc(fechaVencimiento, file);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    @Operation(description = "consulta terminos y condiciones x id si existe",
            summary = "consulta terminos y condiciones x id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<TerminosYCondicionesDTO> buscarTyc(@PathVariable Long id){
        TerminosYCondicionesDTO tyc = terminosYCondicionesService.buscarPorId(id);
        if (tyc == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tyc);
    }

    @GetMapping("/paginado")
    @Operation(description = "consulta todos los terminos y condiciones paginado",
            summary = "consulta todos los terminos y condiciones paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO<TerminosYCondicionesDTO>> buscarTodosPaginado(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        PageableResponseDTO<TerminosYCondicionesDTO> tyc = terminosYCondicionesService.verTodosPaginado(page, size);
        if (tyc.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tyc);
    }
}
