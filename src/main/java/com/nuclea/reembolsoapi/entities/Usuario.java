package com.nuclea.reembolsoapi.entities;

import com.nuclea.reembolsoapi.entities.dtos.UsuarioDto;
import com.nuclea.reembolsoapi.entities.dtos.UsuarioDtoResponse;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String cpf;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Reembolso> reembolsos;

    public void addreembolso(Reembolso reembolso){
        reembolsos.add(reembolso);
    }

    public static Usuario convertEntityResponse(UsuarioDtoResponse usuarioDtoResponse){
        Usuario usuario = new Usuario();
        usuario.setCpf(usuarioDtoResponse.getCpf());
        usuario.setId(usuarioDtoResponse.getId());
        usuario.setReembolsos(usuarioDtoResponse.getReembolsos().stream().map(reembolsoResponseDto -> Reembolso.convertEntityResponse(reembolsoResponseDto)).toList());
        return usuario;
    }

    public static Usuario convertEntityRequest(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setCpf(usuarioDto.getCpf());
        usuario.setNome(usuarioDto.getNome());
        usuario.setReembolsos(usuarioDto.getReembolsos().stream().map(reembolsoResponseDto -> Reembolso.convertEntityResponse(reembolsoResponseDto)).toList());
        return usuario;
    }



}
