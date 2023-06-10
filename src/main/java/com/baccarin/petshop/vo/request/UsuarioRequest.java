package com.baccarin.petshop.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

	private Long id;
	private String nome;
	private Long idCliente;
	private String perfil;
	private String senha;

}
