package com.baccarin.petshop.vo.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContatoRequest {

	private Long id;
	private Long idCliente;
	private String tag;
	private String tipo;
	private BigDecimal valor;
	
}
