package com.baccarin.petshop.vo.filtro;

import java.util.List;

import com.baccarin.petshop.vo.request.PetRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtendimentoFiltro {

	private Long id;
	private List<PetRequest> pets;

}
