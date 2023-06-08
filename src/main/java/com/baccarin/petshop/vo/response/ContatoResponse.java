package com.baccarin.petshop.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContatoResponse {

	private Long id;
	private Long idCliente;
	private String nomeCliente;
	private String tipo;
	private String tag;
	
}
