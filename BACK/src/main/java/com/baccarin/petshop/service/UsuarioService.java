package com.baccarin.petshop.service;

import java.util.List;

import com.baccarin.petshop.vo.filtro.UsuarioFiltro;
import com.baccarin.petshop.vo.request.UsuarioRequest;
import com.baccarin.petshop.vo.response.UsuarioResponse;

public interface UsuarioService {

	void salvarUsuario(UsuarioRequest request) throws Exception ;

	void excluirUsuario(UsuarioRequest request) throws Exception ;

	List<UsuarioResponse> buscaListaUsuarios(UsuarioFiltro filtro) throws Exception ;

}
