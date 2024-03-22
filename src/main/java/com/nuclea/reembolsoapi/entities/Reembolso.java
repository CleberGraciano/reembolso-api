package com.nuclea.reembolsoapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nuclea.reembolsoapi.entities.dtos.ReembolsoRequestDto;
import com.nuclea.reembolsoapi.entities.dtos.ReembolsoResponseDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "reembolso")
public class Reembolso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonIgnore
    private LocalDateTime dataCadastroReembolso;

    @OneToMany(mappedBy = "reembolso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventoStatus> eventoStatus;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private float valorReembolso;


    public static Reembolso convertEntityRequest( ReembolsoRequestDto reembolsoResponseDto){
        Reembolso reembolso = new Reembolso();
        reembolso.setId(reembolsoResponseDto.getId());
        reembolso.setEventoStatus(reembolsoResponseDto.getEventoStatus().stream().map(eventoDto -> EventoStatus.convertEntity(eventoDto)).toList());
        reembolso.setDataCadastroReembolso(reembolsoResponseDto.getDataCadastroReembolso());
        reembolso.setValorReembolso(reembolsoResponseDto.getValorReembolso());
        return reembolso;
    }

    public static Reembolso convertEntityResponse( ReembolsoResponseDto reembolsoResponseDto){
        Reembolso reembolso = new Reembolso();
        reembolso.setId(reembolsoResponseDto.getId());
        reembolso.setEventoStatus(reembolsoResponseDto.getEventoStatusDto().stream().map(eventoDto -> EventoStatus.convertEntity(eventoDto)).toList());
        reembolso.setDataCadastroReembolso(reembolsoResponseDto.getDataCadastroReembolso());
        reembolso.setValorReembolso(reembolsoResponseDto.getValorReembolso());
        return reembolso;
    }

}
