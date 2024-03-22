package com.nuclea.reembolsoapi.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nuclea.reembolsoapi.entities.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioDto {

    private long id;
    private String nome;
    private String cpf;

    @JsonIgnore
    private List<ReembolsoResponseDto> reembolsos;

    public static UsuarioDto convertDto(Usuario usuario){
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setCpf(usuario.getCpf());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setReembolsos(usuario.getReembolsos().stream().map(reembolso -> ReembolsoResponseDto.convertDto(reembolso)).toList());
        usuarioDto.setId(usuarioDto.getId());
        return usuarioDto;
    }

}
