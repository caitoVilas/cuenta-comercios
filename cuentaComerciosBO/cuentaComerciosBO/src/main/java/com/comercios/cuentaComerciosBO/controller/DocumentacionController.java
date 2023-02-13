package com.comercios.cuentaComerciosBO.controller;

import com.comercios.cuentaComerciosBO.dto.ComercioDTO;
import com.comercios.cuentaComerciosBO.dto.DocumentacionDTO;
import com.comercios.cuentaComerciosBO.service.contract.DocumentacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuenta-comercios-bo/documentacion")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Cuenta Comercios BO - Documentacion")
public class DocumentacionController {
    @Autowired
    private DocumentacionService documentacionService;

    @GetMapping("/{comercioId}")
    @Operation(description = "consulta todos los ´documentos de un comercio",
            summary = "consulta todos los documentos de un comercio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<DocumentacionDTO>> buscarPorComercio(@PathVariable Long comercioId){
        List<DocumentacionDTO> documentos = documentacionService.buscarPorComercio(comercioId);
        if (documentos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(documentos);
    }
}
