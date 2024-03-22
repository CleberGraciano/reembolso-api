package com.nuclea.reembolsoapi.services;


import com.nuclea.reembolsoapi.entities.dtos.ReembolsoRequestDto;
import com.nuclea.reembolsoapi.entities.dtos.ReembolsoResponseDto;
import com.nuclea.reembolsoapi.entities.Reembolso;
import com.nuclea.reembolsoapi.entities.StatusReembolso;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ReembolsoService {

    ResponseEntity<ReembolsoResponseDto> solicitarReembolso(ReembolsoRequestDto reembolso);
    ReembolsoResponseDto acompanharReembolsoPorId(long id);
    void cancelarReembolso(long id);
    ReembolsoResponseDto alterarStatusReembolso(long id, StatusReembolso statusReembolso);

    ReembolsoResponseDto acompanharReembolsoPorUsuario(long idReembolso, String cpf);

    List<Reembolso> listarTodosReembolsosPorUsuario(String cpf);

}
