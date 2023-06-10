package com.baccarin.petshop.vo.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtendimentoResponse {

	private Long id;
	private PetReponse pet;
	private String descricao;
	private BigDecimal valor;
	
	public AtendimentoResponse(Long id, Long idPet, String nomePet, String descricao, BigDecimal valor) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.pet = PetReponse.builder().id(idPet).nome(nomePet).build();
	}

}
