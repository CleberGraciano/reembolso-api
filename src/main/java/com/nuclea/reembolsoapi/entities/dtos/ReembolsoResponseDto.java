package com.nuclea.reembolsoapi.entities.dtos;

import com.nuclea.reembolsoapi.entities.Reembolso;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReembolsoResponseDto {

    private long id;

    private LocalDateTime dataCadastroReembolso;


    private List<EventoStatusDto> eventoStatusDto;

    private float valorReembolso;

    public static ReembolsoResponseDto convertDto(Reembolso reembolso){
        ReembolsoResponseDto reembolsoResponseDto = new ReembolsoResponseDto();
        reembolsoResponseDto.setId(reembolso.getId());
        reembolsoResponseDto.setEventoStatusDto(reembolso.getEventoStatus().stream().map(eventoStatus -> EventoStatusDto.convertDto(eventoStatus)).toList());
        reembolsoResponseDto.setDataCadastroReembolso(reembolso.getDataCadastroReembolso());
        reembolsoResponseDto.setValorReembolso(reembolso.getValorReembolso());
        return reembolsoResponseDto;
    }


}
