package com.restarauntHelper.io.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restarauntHelper.io.model.entities.UsuarioGarcom;

public interface UsuarioGarcomRepositories extends JpaRepository<UsuarioGarcom, Long>{

	@Query(name = "SELECT ug FROM UsuarioGarcom ug")
	Set<UsuarioGarcom> listarTodosGarcons();
	
	@Query("SELECT ug FROM UsuarioGarcom ug WHERE ug.nome =: nome")
	UsuarioGarcom findByNome(@Param("nome") String nome);
	
	
	
}
