package com.baccarin.petshop.service;

import java.util.List;

import com.baccarin.petshop.vo.filtro.AtendimentoFiltro;
import com.baccarin.petshop.vo.request.AtendimentoRequest;
import com.baccarin.petshop.vo.response.AtendimentoResponse;

public interface AtendimentoService {

	List<AtendimentoResponse> buscarListaAtendimento(AtendimentoFiltro filtro) throws Exception ;

	void salvarAtendimento(AtendimentoRequest request)  throws Exception ;

}
