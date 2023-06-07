package com.baccarin.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baccarin.petshop.domain.Raca;
import com.baccarin.petshop.vo.response.RacaResponse;

public interface RacaRepository extends JpaRepository<Raca, Long> {

	@Query(" select new com.baccarin.petshop.response.RacaResponse (r.id , r.descricao) from Raca r where r.id > 0")
	List<RacaResponse> buscaListaRacas();

}
