package com.baccarin.petshop.vo.filtro;

import java.util.ArrayList;
import java.util.List;

import com.baccarin.petshop.vo.request.ClienteRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContatoFiltro {

	private Long id;
	private List<String> tipos = new ArrayList<>();
	private List<ClienteRequest> clientes = new ArrayList<>();
	private String tag;
}
