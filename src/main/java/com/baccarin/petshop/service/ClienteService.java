package com.baccarin.petshop.service;

import java.util.List;

import com.baccarin.petshop.vo.filtro.ClienteFiltro;
import com.baccarin.petshop.vo.request.ClienteRequest;
import com.baccarin.petshop.vo.response.ClienteResponse;

public interface ClienteService {

	List<ClienteResponse> buscaListaClientes(ClienteFiltro filtro) throws Exception;

	void salvarCliente(ClienteRequest request) throws Exception;

	void excluirCliente(ClienteRequest request) throws Exception;

}
