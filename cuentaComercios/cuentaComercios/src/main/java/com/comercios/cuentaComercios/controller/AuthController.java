package com.comercios.cuentaComercios.controller;

import com.comercios.cuentaComercios.dto.LoginDTO;
import com.comercios.cuentaComercios.dto.LoginResponseDTO;
import com.comercios.cuentaComercios.dto.UsuarioDTO;
import com.comercios.cuentaComercios.dto.UsuarioNuevoDTO;
import com.comercios.cuentaComercios.service.contract.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cuenta-comercios/auth")
@Tag(name = "Cuenta Comercio -  Autorizacion")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Operation(description = "registro de usuarios", summary = "registro de usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<UsuarioDTO> registroUsuario(@RequestBody UsuarioNuevoDTO dto){
        return ResponseEntity.ok(usuarioService.crearusuario(dto));
    }

    @PostMapping("/login")
    @Operation(description = "login de usuarios", summary = "login de usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO dto){

        return ResponseEntity.ok(usuarioService.login(dto));
    }
}
