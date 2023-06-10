package com.baccarin.petshop.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponse {

	private Long id;
	private Long idCliente;
	private String nomeCliente;
	private String nomeUsuario;
	private String perfil;
	private String senha;
	
	
	public UsuarioResponse(Long id, Long idCliente, String nomeCliente, String nomeUsuario, String perfil) {
		this.id = id;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.nomeUsuario = nomeUsuario;
		this.perfil = perfil;
	}
	
	
}
