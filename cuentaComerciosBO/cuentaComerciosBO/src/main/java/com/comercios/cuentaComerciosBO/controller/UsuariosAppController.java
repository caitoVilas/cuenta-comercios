package com.comercios.cuentaComerciosBO.controller;

import com.comercios.cuentaComerciosBO.dto.ComercioDTO;
import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.UsuarioAppDTO;
import com.comercios.cuentaComerciosBO.dto.UsuarioDTO;
import com.comercios.cuentaComerciosBO.service.contract.ComercioService;
import com.comercios.cuentaComerciosBO.service.contract.UsuariosAppService;
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
@RequestMapping("/api/v1/cuenta-comercios-bo/usuariosApp")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Cuenta Comercios BO - Usuarios App")
public class UsuariosAppController {
    @Autowired
    private UsuariosAppService usuariosAppService;

    @GetMapping("/{id}")
    @Operation(description = "consulta usuario app x id si existe", summary = "consulta usuario app x id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<UsuarioAppDTO> buscarUsuarioApp(@PathVariable Long id){
        UsuarioAppDTO usuario = usuariosAppService.buscarPorId(id);
        if (usuario == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation(description = "consulta todos los ´usuarios app", summary = "consulta todos los usuarios app")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<UsuarioAppDTO>> buscarTodos(){
        List<UsuarioAppDTO> usuarios = usuariosAppService.verTodos();
        if (usuarios.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/paginado")
    @Operation(description = "consulta todos los usuarios app paginado", summary = "consulta todos los usuarios app paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO<UsuarioAppDTO>> buscarTodosPaginado(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        PageableResponseDTO<UsuarioAppDTO> usuarios = usuariosAppService.verTodosPaginado(page, size);
        if (usuarios.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }
}
