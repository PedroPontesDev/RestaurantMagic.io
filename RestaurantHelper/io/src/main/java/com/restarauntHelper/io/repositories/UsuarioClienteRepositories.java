package com.restarauntHelper.io.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restarauntHelper.io.model.entities.UsuarioCliente;

public interface UsuarioClienteRepositories extends JpaRepository<UsuarioCliente, Long>{

	@Query(name = "SELECT c FROM UsuarioCliente c WHERE c.nomeCliente = :nomeCliente")
	Optional<UsuarioCliente> findByNomeCliente(@Param("nomeCliente") String nomeCliente);
	
	
	
}
