package com.nuclea.reembolsoapi.controllers;

import com.nuclea.reembolsoapi.entities.dtos.UsuarioDto;
import com.nuclea.reembolsoapi.entities.dtos.UsuarioDtoResponse;
import com.nuclea.reembolsoapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public UsuarioDtoResponse salvarUsuario(@RequestBody UsuarioDto usuarioDto){
        return UsuarioDtoResponse.convertDto(usuarioService.salvarUsuario(usuarioDto));
    }
}
