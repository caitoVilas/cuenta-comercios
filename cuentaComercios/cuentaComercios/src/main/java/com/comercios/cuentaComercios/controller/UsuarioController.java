package com.comercios.cuentaComercios.controller;

import com.comercios.cuentaComercios.dto.PageableResponseDTO;
import com.comercios.cuentaComercios.dto.UsuarioDTO;
import com.comercios.cuentaComercios.service.contract.UsuarioService;
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
@RequestMapping("/api/v1/cuenta-comercios/usuarios")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Cuenta Comercios - Usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    @Operation(description = "consulta usuario x id si existe", summary = "consulta usuario x id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id){
        UsuarioDTO usuario = usuarioService.buscarrPorid(id);
        if (usuario == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation(description = "consulta todos los ´usuarios", summary = "consulta todos los usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<UsuarioDTO>> buscarTodos(){
        List<UsuarioDTO> usuarios = usuarioService.verTodos();
        if (usuarios.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/paginado")
    @Operation(description = "consulta todos los usuarios paginado", summary = "consulta todos los usuarios paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO<UsuarioDTO>> buscarTodosPaginado(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        PageableResponseDTO<UsuarioDTO> usuarios = usuarioService.verTodosPaginado(page, size);
        if (usuarios.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "eliminar un usuario por id si existe", summary = "eliminar un usuario por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(null);
    }
}
