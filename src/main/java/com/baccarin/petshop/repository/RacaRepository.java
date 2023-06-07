package com.baccarin.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baccarin.petshop.domain.Raca;

public interface RacaRepository extends JpaRepository<Raca, Long>{

}
