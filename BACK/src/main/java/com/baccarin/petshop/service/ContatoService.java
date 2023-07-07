package com.baccarin.petshop.service;

import java.util.List;

import com.baccarin.petshop.vo.filtro.ContatoFiltro;
import com.baccarin.petshop.vo.request.ContatoRequest;
import com.baccarin.petshop.vo.response.ContatoResponse;

public interface ContatoService {

	void salvarContato(ContatoRequest request) throws Exception;

	void excluirContato(ContatoRequest request) throws Exception;

	List<ContatoResponse> buscaListaContatos(ContatoFiltro filtro) throws Exception;

}
