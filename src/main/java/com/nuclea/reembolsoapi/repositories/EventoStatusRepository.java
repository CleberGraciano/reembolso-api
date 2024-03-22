package com.nuclea.reembolsoapi.repositories;

import com.nuclea.reembolsoapi.entities.EventoStatus;

import com.nuclea.reembolsoapi.entities.StatusReembolso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventoStatusRepository extends JpaRepository<EventoStatus, Long> {

    EventoStatus findByStatusReembolso(StatusReembolso statusReembolso);

}
