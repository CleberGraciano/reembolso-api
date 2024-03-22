package com.nuclea.reembolsoapi.entities.dtos;

import com.nuclea.reembolsoapi.entities.EventoStatus;
import com.nuclea.reembolsoapi.entities.StatusReembolso;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class EventoStatusDto {


    private StatusReembolso statusReembolso;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataAtualizacaoStatus;

    public static EventoStatusDto convertDto(EventoStatus eventoStatus){
        EventoStatusDto eventoStatusDto = new EventoStatusDto();
        eventoStatusDto.setStatusReembolso(eventoStatus.getStatusReembolso());
        eventoStatusDto.setDataAtualizacaoStatus(eventoStatus.getDataAtualizacaoStatus());
        return eventoStatusDto;
    }
}
