package com.nuclea.reembolsoapi.services.impl;

import com.nuclea.reembolsoapi.entities.Usuario;
import com.nuclea.reembolsoapi.entities.dtos.*;
import com.nuclea.reembolsoapi.entities.EventoStatus;
import com.nuclea.reembolsoapi.entities.Reembolso;
import com.nuclea.reembolsoapi.entities.StatusReembolso;
import com.nuclea.reembolsoapi.repositories.EventoStatusRepository;
import com.nuclea.reembolsoapi.repositories.ReembolsoRepository;
import com.nuclea.reembolsoapi.repositories.UsuarioRepository;
import com.nuclea.reembolsoapi.services.ReembolsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReembolsoServiceImpl implements ReembolsoService {

    private ReembolsoRepository reembolsoRepository;
    private EventoStatusRepository eventoStatusRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public ReembolsoServiceImpl(ReembolsoRepository reembolsoRepository, EventoStatusRepository eventoStatusRepository, UsuarioRepository usuarioRepository) {
        this.reembolsoRepository = reembolsoRepository;
        this.eventoStatusRepository = eventoStatusRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ResponseEntity<ReembolsoResponseDto> solicitarReembolso(ReembolsoRequestDto reembolsoRequestDto) {
        Usuario usuario = usuarioRepository.findByCpf(reembolsoRequestDto.getCpfUsuario());
        if (usuario!=null){
            reembolsoRequestDto.setDataCadastroReembolso(LocalDateTime.now());
            EventoStatus eventoStatus = new EventoStatus();
            List<EventoStatusDto> statusEvent = new ArrayList<>();
            eventoStatus.setStatusReembolso(StatusReembolso.SOLICITACAO_EM_ANALISE);
            eventoStatus.setDataAtualizacaoStatus(LocalDateTime.now());
            reembolsoRequestDto.setEventoStatus(statusEvent);
            reembolsoRequestDto.setUsuarioDto(UsuarioDto.convertDto(usuario));

            Reembolso reembolso = reembolsoRepository.save(Reembolso.convertEntityRequest(reembolsoRequestDto));

            this.salvarEvento(eventoStatus, reembolso);
            reembolso.setUsuario(usuario);
            usuario.getReembolsos().add(reembolso);
            this.atualizarUsuario(usuario.getId(), usuario);

            return ResponseEntity.accepted().body(ReembolsoResponseDto.convertDto(reembolso));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    private Usuario atualizarUsuario(long usuarioId, Usuario usuario) {

        Usuario usuarioExistente = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setCpf(usuario.getCpf());
            usuarioExistente.setReembolsos(usuario.getReembolsos());
            return usuarioRepository.save(usuarioExistente);
        } else {
            return null;
        }
    }

    private EventoStatus salvarEvento(EventoStatus eventoStatus, Reembolso reembolso){
        eventoStatus.setReembolso(reembolso);
        eventoStatus.setReembolso(reembolso);
        return eventoStatusRepository.save(eventoStatus);
    }


    @Override
    public ReembolsoResponseDto acompanharReembolsoPorId(long id) {

        Reembolso reembolso = reembolsoRepository.findById(id).get();
        return ReembolsoResponseDto.convertDto(reembolso);
    }

    @Override
    public void cancelarReembolso(long id) {

      reembolsoRepository.deleteById(id);
    }

    @Override
    public ReembolsoResponseDto alterarStatusReembolso(long id, StatusReembolso statusReembolso) {
        if (eventoStatusRepository.findByStatusReembolso(statusReembolso)==null){
            return ReembolsoResponseDto.convertDto(reembolsoRepository.findById(id).map(reembolso ->{
                EventoStatus eventoStatus = new EventoStatus();
                eventoStatus.setReembolso(reembolso);
                eventoStatus.setStatusReembolso(statusReembolso);
                eventoStatus.setDataAtualizacaoStatus(LocalDateTime.now());
                reembolso.getEventoStatus().add(eventoStatus);

                return reembolsoRepository.save(reembolso);
            }).orElseThrow());
        }else {
            return null;
        }

    }

    @Override
    public ReembolsoResponseDto acompanharReembolsoPorUsuario(long idReembolso, String cpf) {
        return ReembolsoResponseDto.convertDto(reembolsoRepository.findByIdAndUsuarioCpf(idReembolso, cpf));
    }

    public List<Reembolso> listarTodosReembolsosPorUsuario(String cpf){
        return reembolsoRepository.findByUsuarioCpf(cpf);
    }

}
