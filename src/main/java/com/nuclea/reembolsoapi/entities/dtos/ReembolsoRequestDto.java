package com.nuclea.reembolsoapi.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nuclea.reembolsoapi.entities.Reembolso;

import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReembolsoRequestDto {

    @JsonIgnore
    private long id;

    @JsonIgnore
    private LocalDateTime dataCadastroReembolso;

    @JsonIgnore
    private List<EventoStatusDto> eventoStatus;

    private String cpfUsuario;

    private float valorReembolso;

    @JsonIgnore
    private UsuarioDto usuarioDto;


    public static ReembolsoRequestDto convertDto(Reembolso reembolso){
        ReembolsoRequestDto reembolsoRequestDto = new ReembolsoRequestDto();
        reembolsoRequestDto.setId(reembolso.getId());
        reembolsoRequestDto.setEventoStatus(reembolso.getEventoStatus().stream().map(eventoStatus1 -> EventoStatusDto.convertDto(eventoStatus1)).toList());
        reembolsoRequestDto.setDataCadastroReembolso(reembolso.getDataCadastroReembolso());
        reembolsoRequestDto.setValorReembolso(reembolso.getValorReembolso());
        reembolsoRequestDto.setUsuarioDto(UsuarioDto.convertDto(reembolso.getUsuario()));
        return reembolsoRequestDto;
    }
}
