package com.baccarin.petshop.service;

import java.util.List;

import com.baccarin.petshop.vo.filtro.EnderecoFiltro;
import com.baccarin.petshop.vo.request.EnderecoRequest;
import com.baccarin.petshop.vo.response.EnderecoResponse;

public interface EnderecoService {

	List<EnderecoResponse> buscaListaEnderecos(EnderecoFiltro filtro) throws Exception;

	void salvarEndereco(EnderecoRequest request) throws Exception;

	void excluirEndereco(EnderecoRequest request) throws Exception;

}
