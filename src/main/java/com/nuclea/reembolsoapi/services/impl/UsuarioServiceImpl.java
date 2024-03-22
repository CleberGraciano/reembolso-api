package com.nuclea.reembolsoapi.services.impl;

import com.nuclea.reembolsoapi.entities.Reembolso;
import com.nuclea.reembolsoapi.entities.Usuario;
import com.nuclea.reembolsoapi.entities.dtos.ReembolsoResponseDto;
import com.nuclea.reembolsoapi.entities.dtos.UsuarioDto;
import com.nuclea.reembolsoapi.entities.dtos.UsuarioDtoResponse;
import com.nuclea.reembolsoapi.repositories.UsuarioRepository;
import com.nuclea.reembolsoapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario salvarUsuario(UsuarioDto usuarioDto) {
        List<ReembolsoResponseDto> lista = new ArrayList<>();
        usuarioDto.setReembolsos(lista);
        return usuarioRepository.save(Usuario.convertEntityRequest(usuarioDto));
    }

    @Override
    public ResponseEntity<UsuarioDtoResponse> listUsuarioReembolso(String cpf, long idReembolso){

        Usuario usuario = usuarioRepository.findByCpf(cpf);
        Usuario usuarioEncontrado = (Usuario) usuario.getReembolsos().stream().filter(reembolso -> reembolso.getId()==idReembolso);
        if (usuarioEncontrado!=null){
            return ResponseEntity.accepted().body(UsuarioDtoResponse.convertDto(usuarioEncontrado));
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
