package com.teste.api.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teste.api.model.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByLogin(String login);
	
	Optional<Usuario> findByCpf(String cpf);


}
