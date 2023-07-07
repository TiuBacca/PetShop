package com.baccarin.petshop.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoResponse {

	private Long id;
	private Long idCliente;
	private String nomeCliente;
	private String logradouro;
	private String cidade;
	private String bairro;
	private String complemento;
	private String tag;

}
