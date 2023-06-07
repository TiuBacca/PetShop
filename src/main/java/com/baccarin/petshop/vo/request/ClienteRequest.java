package com.baccarin.petshop.vo.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteRequest {

	private Long id;
	private Long usuarioId;
	private String nome;
	private String cpf;
	private LocalDateTime dataCadastro;
}
