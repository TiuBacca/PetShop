package com.baccarin.petshop.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.exceptions.RegistroNaoEncontradoException;
import com.baccarin.petshop.repository.ContatoRepository;
import com.baccarin.petshop.service.ContatoService;
import com.baccarin.petshop.vo.filtro.ContatoFiltro;
import com.baccarin.petshop.vo.request.ClienteRequest;
import com.baccarin.petshop.vo.request.ContatoRequest;
import com.baccarin.petshop.vo.response.ContatoResponse;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ContatoServiceImpl implements ContatoService {

	private final ContatoRepository contatoRepository;
	private final Util util;

	@Override
	public void salvarContato(ContatoRequest request) throws Exception {
		validaSalvarContato(request);
		contatoRepository.save(ContatoRequest.convertToDomain(request));
	}

	@Override
	public void excluirContato(ContatoRequest request) throws Exception {
		validaExcluirContato(request);
		contatoRepository.deleteById(request.getId());
	}

	private void validaExcluirContato(ContatoRequest request) throws Exception {
		if (Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			contatoRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
					"Contato equivalente ao código informado não foi encontrado."));
		}

		throw new RegistroIncompletoException("Para remover o contato, é necessário informar o seu código.");
	}

	private void validaSalvarContato(ContatoRequest request) throws Exception {
		if (Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			contatoRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
					"Contato equivalente ao código informado não foi encontrado."));
		} else {
			if (!Util.validaDiferenteNullAndDiferenteZero(request.getIdCliente())) {
				throw new RegistroIncompletoException(
						"Para salvar um novo contato, é necessário informar o cliente proprietário dele.");
			}

			if (StringUtils.isBlank(request.getTipo())) {
				throw new RegistroIncompletoException(
						"Para salvar um novo contato, é necessário informar o tipo dele.");
			}
		}

	}

	@Override
	public List<ContatoResponse> buscaListaContatos(ContatoFiltro filtro) throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append(
				" select new com.baccarin.petshop.vo.response.ContatoResponse (c.id, c.cliente.id, c.cliente.nome, c.tipo, c.tag ) from Contato c \n"
						+ " WHERE c.id > 0 ");

		if (Util.validaDiferenteNullAndDiferenteZero(filtro.getId())) {
			sb.append(" AND c.id = :id");
		}

		if (StringUtils.isNotBlank(filtro.getTag())) {
			sb.append(" AND c.tag like :tag \n");
		}

		if (Objects.nonNull(filtro.getClientes()) && !filtro.getClientes().isEmpty()) {
			sb.append(" AND c.cliente.id in (:idClientes) \n ");
		}

		if (Objects.nonNull(filtro.getTipos()) && !filtro.getTipos().isEmpty()) {
			sb.append(" AND c.tipo in (:tipos) \n ");
		}

		TypedQuery<ContatoResponse> query = util.getEntityManager().createQuery(sb.toString(), ContatoResponse.class);

		if (Util.validaDiferenteNullAndDiferenteZero(filtro.getId())) {
			query.setParameter("id", filtro.getId());
		}

		if (StringUtils.isNotBlank(filtro.getTag())) {
			query.setParameter("tag", filtro.getTag());
		}

		if (Objects.nonNull(filtro.getClientes()) && !filtro.getClientes().isEmpty()) {
			List<Long> idClientes = filtro.getClientes().stream().map(ClienteRequest::getId)
					.collect(Collectors.toList());
			if (!idClientes.isEmpty()) {
				query.setParameter("idClientes", idClientes);
			}
		}

		if (Objects.nonNull(filtro.getTipos()) && !filtro.getTipos().isEmpty()) {
			query.setParameter("idClientes", filtro.getTipos());

		}

		return query.getResultList();

	}
}
