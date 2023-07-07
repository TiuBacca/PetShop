package com.baccarin.petshop.vo.filtro;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoFiltro {

	private Long id;
	private String logradouro;
	private List<String> cidades = new ArrayList<>();
	private String bairro;
	private String complemento;
	private String tag;
}
