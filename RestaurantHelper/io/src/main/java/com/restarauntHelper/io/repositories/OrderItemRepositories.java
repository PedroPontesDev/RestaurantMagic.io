package com.restarauntHelper.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restarauntHelper.io.model.entities.ItemPedido;

@Repository
public interface OrderItemRepositories extends JpaRepository<ItemPedido, Long>{

}
