package com.baccarin.petshop.vo.filtro;

import java.util.List;

import com.baccarin.petshop.vo.request.ClienteRequest;
import com.baccarin.petshop.vo.response.RacaResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetFiltro {

	private Long id;
	private List<ClienteRequest> clientes;
	private List<RacaResponse> racas;
	private String nome;
}
