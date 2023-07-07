package com.baccarin.petshop.service;

import java.util.List;

import com.baccarin.petshop.vo.filtro.PetFiltro;
import com.baccarin.petshop.vo.request.PetRequest;
import com.baccarin.petshop.vo.response.PetReponse;

public interface PetService {

	List<PetReponse> buscaListaPet(PetFiltro filtro) throws Exception;

	void salvarPet(PetRequest request) throws Exception;

	void excluirPet(PetRequest request) throws Exception;

}
