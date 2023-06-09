package com.baccarin.petshop.vo.request;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.baccarin.petshop.domain.Cliente;
import com.baccarin.petshop.domain.Endereco;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoRequest {

	private Long id;
	private Long idCliente;
	private String logradouro;
	private String cidade;
	private String bairro;
	private String complemento;
	private String tag;

	public static Endereco convertToDomain(EnderecoRequest request) {
		Endereco endereco = new Endereco();

		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			endereco.setId(request.getId());
		}

		if (Objects.nonNull(request.getIdCliente()) && request.getIdCliente() != 0) {
			endereco.setCliente(Cliente.builder().id(request.getIdCliente()).build());
		}

		if (StringUtils.isNotBlank(request.getLogradouro())) {
			endereco.setLogradouro(request.getLogradouro());
		}

		if (StringUtils.isNotBlank(request.getCidade())) {
			endereco.setLogradouro(request.getCidade());
		}

		if (StringUtils.isNotBlank(request.getBairro())) {
			endereco.setLogradouro(request.getBairro());
		}

		if (StringUtils.isNotBlank(request.getComplemento())) {
			endereco.setLogradouro(request.getComplemento());
		}

		if (StringUtils.isNotBlank(request.getTag())) {
			endereco.setTag(request.getTag());
		}

		return endereco;
	}

}
