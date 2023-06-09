package com.baccarin.petshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.exceptions.RegistroNaoEncontradoException;
import com.baccarin.petshop.repository.EnderecoRepository;
import com.baccarin.petshop.service.EnderecoService;
import com.baccarin.petshop.vo.filtro.EnderecoFiltro;
import com.baccarin.petshop.vo.request.ClienteRequest;
import com.baccarin.petshop.vo.request.EnderecoRequest;
import com.baccarin.petshop.vo.response.EnderecoResponse;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

	private final EnderecoRepository enderecoRepository;
	private final Util util;

	@Override
	public List<EnderecoResponse> buscaListaEnderecos(EnderecoFiltro filtro) throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append(
				" select new com.baccarin.petshop.vo.response.EnderecoResponse (e.id, e.cliente.id, e.cliente.nome, e.logradouro, e.cidade, coalesce(e.bairro, 'Sem informação') , coalesce(e.complemento, 'Sem informação'), e.tag ) from Endereco e \n"
						+ " WHERE e.id > 0 ");

		if (Util.validaDiferenteNullAndDiferenteZero(filtro.getId())) {
			sb.append(" AND e.id = :id");
		}

		if (StringUtils.isNotBlank(filtro.getLogradouro())) {
			sb.append(" AND e.logradouro like :logradouro \n ");
		}

		if (Objects.nonNull(filtro.getCidades()) && !filtro.getCidades().isEmpty()) {
			sb.append(" AND e.cidade in (:cidades) \n ");
		}

		if (StringUtils.isNotBlank(filtro.getBairro())) {
			sb.append(" AND e.bairro like :bairro \n");
		}

		if (StringUtils.isNotBlank(filtro.getComplemento())) {
			sb.append(" AND e.complemento like :complemento \n");
		}

		if (StringUtils.isNotBlank(filtro.getTag())) {
			sb.append(" AND e.tag like :tag \n");
		}

		TypedQuery<EnderecoResponse> query = util.getEntityManager().createQuery(sb.toString(), EnderecoResponse.class);

		if (Util.validaDiferenteNullAndDiferenteZero(filtro.getId())) {
			query.setParameter("id", filtro.getId());
		}

		if (StringUtils.isNotBlank(filtro.getLogradouro())) {
			query.setParameter("logradouro", filtro.getLogradouro());
		}

		if (Objects.nonNull(filtro.getCidades()) && !filtro.getCidades().isEmpty()) {
			query.setParameter("cidades", filtro.getCidades());

		}

		if (StringUtils.isNotBlank(filtro.getBairro())) {
			query.setParameter("bairro", filtro.getBairro());
		}

		if (StringUtils.isNotBlank(filtro.getComplemento())) {
			query.setParameter("complemento", filtro.getComplemento());
		}

		if (StringUtils.isNotBlank(filtro.getTag())) {
			query.setParameter("tag", filtro.getTag());
		}

		return query.getResultList();
	}

	@Override
	public void salvarEndereco(EnderecoRequest request) throws Exception {
		validaSalvarEndereco(request);
		enderecoRepository.save(EnderecoRequest.convertToDomain(request));
	}

	@Override
	public void excluirEndereco(EnderecoRequest request) throws Exception {
		validaExcluirEndereco(request);
		enderecoRepository.deleteById(request.getId());
	}

	private void validaExcluirEndereco(EnderecoRequest request) throws Exception {
		if (Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			enderecoRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
					"Endereço equivalente ao código informado não foi encontrado."));
		}

		throw new RegistroIncompletoException("Para remover o endereço, é necessário informar o seu código.");
	}

	private void validaSalvarEndereco(EnderecoRequest request) throws Exception {

		if (Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			enderecoRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
					"Endereço equivalente ao código informado não foi encontrado."));
		} else {
			if (!Util.validaDiferenteNullAndDiferenteZero(request.getIdCliente())) {
				throw new RegistroIncompletoException(
						"Para salvar um novo endereço, é necessário informar o cliente proprietário dele.");
			}

			if (StringUtils.isBlank(request.getLogradouro())) {
				throw new RegistroIncompletoException(
						"Para salvar um novo endereço, é necessário informar o logradouro dele.");
			}

			if (StringUtils.isBlank(request.getCidade())) {
				throw new RegistroIncompletoException(
						"Para salvar um novo endereço, é necessário informar a cidade dele.");
			}

		}
	}
}
