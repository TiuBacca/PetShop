package com.baccarin.petshop.vo.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponse {

	private Long id;
	private String cpf;
	private String nome;
	
	@JsonProperty("dataCadastro")
	private LocalDateTime dataCadastro;
}
