package com.restarauntHelper.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restarauntHelper.io.model.entities.Mesa;

@Repository
public interface MesaRepositories extends JpaRepository<Mesa, Long>{


}
