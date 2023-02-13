package com.comercios.cuentaComercios.controller;

import com.comercios.cuentaComercios.dto.*;
import com.comercios.cuentaComercios.enums.DocumentacionTipo;
import com.comercios.cuentaComercios.service.contract.ComercioService;
import com.comercios.cuentaComercios.service.impl.ComercioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuenta-comercios/comercios")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Cuenta Comercios - Comercios")
public class ComercioController {
    private static final Logger logger = LoggerFactory.getLogger(ComercioServiceImpl.class);
    @Autowired
    private ComercioService comercioService;

    @PostMapping
    @Operation(description = "alta de comercios", summary = "alta de comercios")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<ComercioDTO> registroUsuario(@RequestBody ComercioNuevoDTO dto){
        return ResponseEntity.ok(comercioService.crearComercio(dto));
    }

    @GetMapping("/{id}")
    @Operation(description = "consulta comercio x id si existe", summary = "consulta comercio x id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<ComercioDTO> buscarPorId(@PathVariable Long id){
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
        PageableResponseDTO<ComercioDTO> usuarios = comercioService.verTodosPaginado(page, size);
        if (usuarios.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
                 produces =  {MediaType.APPLICATION_JSON_VALUE},
                 path = "/documento/{comercioId}/{tipoDocumentacion}")
    @Operation(description = "carga de documentos", summary = "carga de documentos")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<?> subirDocumento(@RequestPart("file") MultipartFile file,
                                            @PathVariable Long comercioId,
                                            @PathVariable DocumentacionTipo tipoDocumentacion){
        logger.info("entrada subida archivo");
        comercioService.guardarDocumento(file, comercioId, tipoDocumentacion);
        return ResponseEntity.ok(null);
    }
}
