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
import com.baccarin.petshop.repository.PetRepository;
import com.baccarin.petshop.service.PetService;
import com.baccarin.petshop.vo.filtro.PetFiltro;
import com.baccarin.petshop.vo.request.ClienteRequest;
import com.baccarin.petshop.vo.request.PetRequest;
import com.baccarin.petshop.vo.response.ContatoResponse;
import com.baccarin.petshop.vo.response.PetReponse;
import com.baccarin.petshop.vo.response.RacaResponse;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

	private final PetRepository petRepository;
	private final Util util;

	@Override
	public List<PetReponse> buscaListaPet(PetFiltro filtro) throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append(
				" select new com.baccarin.petshop.vo.response.PetReponse (p.id, p.cliente.id, p.cliente.nome, p.raca, p.dataNascimento, p.nome ) from Pet p \n"
						+ " WHERE p.id > 0 ");

		if (Util.validaDiferenteNullAndDiferenteZero(filtro.getId())) {
			sb.append(" AND p.id = :id");
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			sb.append(" AND p.nome like :nome \n");
		}

		if (Objects.nonNull(filtro.getClientes()) && !filtro.getClientes().isEmpty()) {
			sb.append(" AND p.cliente.id in (:idClientes) \n ");
		}

		if (Objects.nonNull(filtro.getRacas()) && !filtro.getRacas().isEmpty()) {
			sb.append(" AND p.raca.id in (:racas) \n ");
		}

		TypedQuery<PetReponse> query = util.getEntityManager().createQuery(sb.toString(), PetReponse.class);

		if (Util.validaDiferenteNullAndDiferenteZero(filtro.getId())) {
			query.setParameter("id", filtro.getId());
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			query.setParameter("nome", filtro.getNome());
		}

		if (Objects.nonNull(filtro.getClientes()) && !filtro.getClientes().isEmpty()) {
			List<Long> idClientes = filtro.getClientes().stream().map(ClienteRequest::getId)
					.collect(Collectors.toList());
			if (!idClientes.isEmpty()) {
				query.setParameter("idClientes", idClientes);
			}
		}

		if (Objects.nonNull(filtro.getRacas()) && !filtro.getRacas().isEmpty()) {
			List<Long> idRacas = filtro.getRacas().stream().map(RacaResponse::getId).collect(Collectors.toList());
			if (!idRacas.isEmpty()) {
				query.setParameter("racas", idRacas);
			}
		}

		return query.getResultList();
	}

	@Override
	public void salvarPet(PetRequest request) throws Exception {
		validaSalvarPet(request);
		petRepository.save(PetRequest.converToDomain(request));
	}

	@Override
	public void excluirPet(PetRequest request) throws Exception {
		if (Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			petRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
					"Pet equivalente ao código informado não foi encontrado."));
		}

		throw new RegistroIncompletoException("Para remover o pet, é necessário informar o seu código.");
	}

	private void validaSalvarPet(PetRequest request) throws Exception {
		if (Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			petRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
					"Pet equivalente ao código informado não foi encontrado."));
		} else {
			if (!Util.validaDiferenteNullAndDiferenteZero(request.getIdCliente())) {
				throw new RegistroIncompletoException(
						"Para salvar um novo pet, é necessário informar o cliente proprietário dele.");
			}

			if (!Util.validaDiferenteNullAndDiferenteZero(request.getIdRaca())) {
				throw new RegistroIncompletoException("Para salvar um novo pet, é necessário informar a raça dele.");
			}

			if (StringUtils.isBlank(request.getNome())) {
				throw new RegistroIncompletoException("Para salvar um novo pet, é necessário informar o nome dele.");
			}
		}

	}

	private void validaExcluirPet(PetRequest request) throws Exception {
		if (Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			petRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
					"Pet equivalente ao código informado não foi encontrado."));
		}

		throw new RegistroIncompletoException("Para remover o pet, é necessário informar o seu código.");
	}
	}

}
