package com.nuclea.reembolsoapi.repositories;

import com.nuclea.reembolsoapi.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCpf(String cpfUsuario);
}
