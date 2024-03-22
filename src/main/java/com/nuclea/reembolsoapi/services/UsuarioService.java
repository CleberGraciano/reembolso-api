package com.nuclea.reembolsoapi.services;

import com.nuclea.reembolsoapi.entities.Usuario;
import com.nuclea.reembolsoapi.entities.dtos.UsuarioDto;
import com.nuclea.reembolsoapi.entities.dtos.UsuarioDtoResponse;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    Usuario salvarUsuario(UsuarioDto usuarioDto);

    ResponseEntity<UsuarioDtoResponse> listUsuarioReembolso(String cpf, long idReembolso);
}
