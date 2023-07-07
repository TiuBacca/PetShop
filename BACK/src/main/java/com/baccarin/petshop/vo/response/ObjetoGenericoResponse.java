package com.baccarin.petshop.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjetoGenericoResponse {

	private Long id;
	private String descricao;
	
	public ObjetoGenericoResponse(String descricao) {
		this.descricao = descricao;
	}
	
}
