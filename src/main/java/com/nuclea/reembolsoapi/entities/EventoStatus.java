package com.nuclea.reembolsoapi.entities;

import com.nuclea.reembolsoapi.entities.dtos.EventoStatusDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Entity
@Table(name = "eventoStatus")
@Data
public class EventoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private StatusReembolso statusReembolso;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataAtualizacaoStatus;

    @ManyToOne
    @JoinColumn(name = "reembolso_id")
    private Reembolso reembolso;

    public static EventoStatus convertEntity(EventoStatusDto eventoStatusDto){
        EventoStatus eventoStatus = new EventoStatus();
        eventoStatus.setStatusReembolso(eventoStatusDto.getStatusReembolso());
        eventoStatus.setDataAtualizacaoStatus(eventoStatusDto.getDataAtualizacaoStatus());
        return eventoStatus;
    }
}
