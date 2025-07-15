package com.restarauntHelper.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restarauntHelper.io.model.entities.Pedido;

@Repository
public interface PedidoRepositories extends JpaRepository<Pedido, Long>{

}
