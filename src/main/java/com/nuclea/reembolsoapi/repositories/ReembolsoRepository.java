package com.nuclea.reembolsoapi.repositories;

import com.nuclea.reembolsoapi.entities.Reembolso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReembolsoRepository extends JpaRepository<Reembolso, Long> {

    //@Query("select u from User u where u.firstname = :#{#customer.firstname}")
    //List<Reembolso> findUsersByCustomersFirstname(@Param("customer") Customer customer);

    Reembolso findByIdAndUsuarioCpf(long idReemboldo, String cpf);

    List<Reembolso> findByUsuarioCpf(String cpf);



}
