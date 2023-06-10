package com.baccarin.petshop.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.exceptions.RegistroNaoEncontradoException;
import com.baccarin.petshop.repository.AtendimentoRepository;
import com.baccarin.petshop.repository.PetRepository;
import com.baccarin.petshop.service.AtendimentoService;
import com.baccarin.petshop.vo.filtro.AtendimentoFiltro;
import com.baccarin.petshop.vo.request.AtendimentoRequest;
import com.baccarin.petshop.vo.request.PetRequest;
import com.baccarin.petshop.vo.response.AtendimentoResponse;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AtendimentoServiceImpl implements AtendimentoService {

	private final AtendimentoRepository atendimentoRepository;
	private final PetRepository petRepository;
	private final Util util;

	@Override
	public List<AtendimentoResponse> buscarListaAtendimento(AtendimentoFiltro filtro) throws Exception {
		StringBuilder sb = new StringBuilder();

		
		sb.append(
				" select new com.baccarin.petshop.vo.response.AtendimentoResponse (a.id, a.pet.id, a.pet.nome, a.descricao, a.valor ) from Usuario u \n"
						+ " WHERE u.id > 0 ");

		if (Util.validaDiferenteNullAndDiferenteZero(filtro.getId())) {
			sb.append(" AND a.id = :id");
		}

		if (Objects.nonNull(filtro.getPets()) && !filtro.getPets().isEmpty()) {
			sb.append(" AND a.pet.id in (:idPets) \n ");
		}

		TypedQuery<AtendimentoResponse> query = util.getEntityManager().createQuery(sb.toString(), AtendimentoResponse.class);

		if (Util.validaDiferenteNullAndDiferenteZero(filtro.getId())) {
			query.setParameter("id", filtro.getId());
		}

		if (Objects.nonNull(filtro.getPets()) && !filtro.getPets().isEmpty()) {
			List<Long> idPets = filtro.getPets().stream().map(PetRequest::getId)
					.collect(Collectors.toList());
			if (!idPets.isEmpty()) {
				query.setParameter("idPets", idPets);
			}
		}

		return query.getResultList();
	}

	@Override
	public void salvarAtendimento(AtendimentoRequest request) throws Exception {
		validaSalvarAtendimento(request);
		atendimentoRepository.save(AtendimentoRequest.converteToDomain(request));
		
	}
	
	private void validaSalvarAtendimento( AtendimentoRequest request) throws Exception {
		if (Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			atendimentoRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
					"Atendimento equivalente ao código informado não foi encontrado."));
		} else {

			if (Objects.nonNull(request.getPet()) &&   Util.validaDiferenteNullAndDiferenteZero(request.getPet().getId())) {
				petRepository.findById(request.getPet().getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
						"Pet equivalente ao código informado não foi encontrado."));
			}

			if (Objects.isNull(request.getValor())) {
				throw new RegistroIncompletoException(
						"Para salvar um novo atendimento, é necessário informar o valor dele.");
			} 

		}
	}
}
