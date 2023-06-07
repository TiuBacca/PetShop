package com.baccarin.petshop.vo.filtro;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteFiltro {

	private Long id;
	private String nome;
	private String cpf;
	private LocalDateTime dataCadastroInicio;
	private LocalDateTime dataCadastroFim;
	
}
