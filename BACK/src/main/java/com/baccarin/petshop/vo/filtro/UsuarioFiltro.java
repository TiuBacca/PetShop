package com.baccarin.petshop.vo.filtro;

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
public class UsuarioFiltro {

	private Long id;
	private List<ClienteRequest> clientes;
	private List<String> tipos;

}
