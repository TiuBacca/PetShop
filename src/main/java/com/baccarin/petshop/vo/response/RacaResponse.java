package com.baccarin.petshop.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class RacaResponse extends ObjetoGenericoResponse {

	public RacaResponse(Long id, String descricao) {
		super(id,descricao);
	}
}
