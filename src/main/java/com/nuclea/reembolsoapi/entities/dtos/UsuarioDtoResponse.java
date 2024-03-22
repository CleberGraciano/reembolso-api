package com.nuclea.reembolsoapi.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nuclea.reembolsoapi.entities.Reembolso;
import com.nuclea.reembolsoapi.entities.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioDtoResponse {

    @JsonIgnore
    private long id;
    private String nome;
    private String cpf;
    private List<ReembolsoResponseDto> reembolsos;


    public static UsuarioDtoResponse convertDto(Usuario usuario){
        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse();
        usuarioDtoResponse.setCpf(usuario.getCpf());
        usuarioDtoResponse.setNome(usuario.getNome());
        usuarioDtoResponse.setReembolsos(usuario.getReembolsos().stream().map(reembolso -> ReembolsoResponseDto.convertDto(reembolso)).toList());
        usuarioDtoResponse.setId(usuario.getId());
        return usuarioDtoResponse;
    }


}
