package com.baccarin.petshop.vo.request;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.baccarin.petshop.domain.Cliente;
import com.baccarin.petshop.domain.Contato;
import com.baccarin.petshop.enums.TipoContato;

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

	public static Contato convertToDomain(ContatoRequest request) {
		Contato contato = new Contato();

		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			contato.setId(request.getId());
		}

		if (Objects.nonNull(request.getIdCliente()) && request.getIdCliente() != 0) {
			contato.setCliente(Cliente.builder().id(request.getIdCliente()).build());
		}

		if (StringUtils.isNotBlank(request.getTag())) {
			contato.setTag(request.getTag());
		}

		if (StringUtils.isNotBlank(request.getTipo())) {
			contato.setTipo(TipoContato.valueOf(request.getTag()));
		}

		if (Objects.nonNull(request.getValor())) {
			contato.setValor(request.getValor());
		}

		return contato;
	}

}
