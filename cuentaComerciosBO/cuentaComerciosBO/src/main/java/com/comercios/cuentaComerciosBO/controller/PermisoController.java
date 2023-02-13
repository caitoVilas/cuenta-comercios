package com.comercios.cuentaComerciosBO.controller;

import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.PermisoDTO;
import com.comercios.cuentaComerciosBO.dto.PermisoNuevoDTO;
import com.comercios.cuentaComerciosBO.service.contract.PermisoService;
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
@RequestMapping("/api/v1/cuenta-comercios-bo/permisos")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Cuenta Comercios BO - Permisos")
public class PermisoController {
    @Autowired
    private PermisoService permisoService;

    @PostMapping
    @Operation(description = "creacion de permisos", summary = "creacion de permisos")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PermisoDTO> crearPermiso(@RequestBody PermisoNuevoDTO dto){
        return ResponseEntity.ok(permisoService.crearPermiso(dto));
    }

    @GetMapping("/{id}")
    @Operation(description = "consulta permiso x id si existe", summary = "consulta permiso x id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PermisoDTO> buscarPermiso(@PathVariable Long id){
        PermisoDTO permiso = permisoService.buscarXId(id);
        if (permiso == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(permiso);
    }

    @GetMapping
    @Operation(description = "consulta todos los ´permisos", summary = "consulta todos los permisos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<PermisoDTO>> buscarTodos(){
        List<PermisoDTO> permisos = permisoService.buscarTodos();
        if (permisos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(permisos);
    }

    @GetMapping("/paginado")
    @Operation(description = "consulta todos los permisos paginado", summary = "consulta todos los permisos paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO<PermisoDTO>> buscarTodosPaginado(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        PageableResponseDTO<PermisoDTO> permisos = permisoService.buscarTodosPaginado(page, size);
        if (permisos.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(permisos);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "eliminar un permiso por id si existe", summary = "eliminar un permiso por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<?> eliminarPermiso(@PathVariable long id){
        permisoService.eliminarPermiso(id);
        return ResponseEntity.ok(null);
    }
}
