package com.comercios.cuentaComerciosBO.controller;

import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.RolDTO;
import com.comercios.cuentaComerciosBO.dto.RolNuevoDTO;
import com.comercios.cuentaComerciosBO.service.contract.RolService;
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
@RequestMapping("/api/v1/cuenta-comercios-bo/roles")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Cuenta Comercios BO - Roles")
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping
    @Operation(description = "creacion de roles", summary = "creacion de roles")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<RolDTO> crearRol(@RequestBody RolNuevoDTO dto){
        return ResponseEntity.ok(rolService.createRol(dto));
    }

    @GetMapping("/{id}")
    @Operation(description = "consulta rol x id si existe", summary = "consulta rol x id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<RolDTO> buscarXId(@PathVariable Long id){
        RolDTO response = rolService.buscarPorId(id);
        if (response == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(description = "consulta todos los roles", summary = "consulta todos los roles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<RolDTO>> buscarTodos(){
        List<RolDTO> roles = rolService.buscarTodos();
        if (roles.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/paginado")
    @Operation(description = "consulta todos los roles paginado", summary = "consulta todos los roles paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO<RolDTO>> buscarTodosPaginado(
                                                        @RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10") int size){
        PageableResponseDTO<RolDTO> roles = rolService.buscarPaginado(page, size);
        if (roles.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(roles);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "eliminar un rol por id si existe", summary = "eliminar un rol por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<?> eleiminarRol(@PathVariable Long id){
        rolService.eliminarRol(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/permisos/{rolId}")
    @Operation(description = "asignar permisos a un rol", summary = "asignar permisos a un rol")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<RolDTO> asignarPermisos(@PathVariable Long rolId,
                                                  @RequestParam List<Long> permisosId){
        return ResponseEntity.ok(rolService.asignarPermisos(rolId, permisosId));
    }
}
