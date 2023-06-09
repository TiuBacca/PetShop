package com.baccarin.petshop.vo.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetReponse {

	private Long id;
	private Long idCliente;
	private String nomeCliente;
	private RacaResponse raca;
	private LocalDate dataNascimento;
	private String nome;
}
