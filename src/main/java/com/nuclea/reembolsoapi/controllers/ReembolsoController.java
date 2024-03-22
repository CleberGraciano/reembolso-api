package com.nuclea.reembolsoapi.controllers;

import com.nuclea.reembolsoapi.entities.dtos.ReembolsoRequestDto;
import com.nuclea.reembolsoapi.entities.dtos.ReembolsoResponseDto;
import com.nuclea.reembolsoapi.entities.Reembolso;
import com.nuclea.reembolsoapi.entities.StatusReembolso;
import com.nuclea.reembolsoapi.services.ReembolsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reembolsos")
public class ReembolsoController {

    private ReembolsoService reembolsoService;

    @Autowired
    public ReembolsoController(ReembolsoService reembolsoService) {
        this.reembolsoService = reembolsoService;
    }

    @PostMapping()
    public ResponseEntity<ReembolsoResponseDto> solicitarReembolso(@RequestBody ReembolsoRequestDto reembolso){
        return reembolsoService.solicitarReembolso(reembolso);
    }


    @DeleteMapping("/{idReembolso}")
    public void cancelarReembolso(@PathVariable long idReembolso){
        reembolsoService.cancelarReembolso(idReembolso);
    }

    @PutMapping("/{idReembolso}")
    public ReembolsoResponseDto alterarStatusReembolso(@PathVariable long idReembolso, @RequestParam StatusReembolso statusReembolso){
        return reembolsoService.alterarStatusReembolso(idReembolso, statusReembolso);
    }

    @GetMapping("/{idReembolso}/usuarios/{cpf}")
    public ReembolsoResponseDto acompanharReembolsoPorUsuario(@PathVariable long idReembolso, @PathVariable String cpf) {
        return reembolsoService.acompanharReembolsoPorUsuario(idReembolso, cpf);
    }

    @GetMapping("/{cpf}")
    public List<Reembolso>listarTodosReembolsosPorUsuario(String cpf){
        return reembolsoService.listarTodosReembolsosPorUsuario(cpf);
    }
}
