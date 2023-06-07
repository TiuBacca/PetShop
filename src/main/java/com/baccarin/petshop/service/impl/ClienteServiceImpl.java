package com.baccarin.petshop.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.domain.Cliente;
import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.repository.ClienteRepository;
import com.baccarin.petshop.service.ClienteService;
import com.baccarin.petshop.vo.filtro.ClienteFiltro;
import com.baccarin.petshop.vo.request.ClienteRequest;
import com.baccarin.petshop.vo.response.ClienteResponse;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;
	private final Util util;

	@Override
	public List<ClienteResponse> buscaListaClientes(ClienteFiltro filtro) throws Exception {

		StringBuilder sb = new StringBuilder();

		sb.append(
				" select new com.baccarin.petshop.vo.response.ClienteResponse (c.id, c.nome, c.cpf, c.dataCadastro) from Cliente c \n"
						+ " WHERE c.id > 0 ");

		if (StringUtils.isNotBlank(filtro.getNome())) {
			sb.append(" AND c.nome like :nome \n");
		}

		if (StringUtils.isNotBlank(filtro.getCpf())) {
			sb.append(" AND c.cpf like :cpf \n ");
		}

		if (Objects.nonNull(filtro.getId()) && filtro.getId() != 0) {
			sb.append(" AND c.id = :id \n");
		}

		if (Objects.nonNull(filtro.getDataCadastroInicio())) {
			sb.append(" AND c.dataCadastro >= :inicio \n");
		}

		if (Objects.nonNull(filtro.getDataCadastroFim())) {
			sb.append(" AND c.dataCadastro <= :fim \n");
		}

		TypedQuery<ClienteResponse> query = util.getEntityManager().createQuery(sb.toString(), ClienteResponse.class);

		if (StringUtils.isNotBlank(filtro.getNome())) {
			query.setParameter("nome", filtro.getNome());
		}

		if (StringUtils.isNotBlank(filtro.getCpf())) {
			query.setParameter("cpf", filtro.getCpf());
		}

		if (Objects.nonNull(filtro.getId()) && filtro.getId() != 0) {
			query.setParameter("id", filtro.getId());
		}

		if (Objects.nonNull(filtro.getDataCadastroInicio())) {
			query.setParameter("inicio", filtro.getDataCadastroInicio());
		}

		if (Objects.nonNull(filtro.getDataCadastroFim())) {
			query.setParameter("fim", filtro.getDataCadastroFim());
		}

		return query.getResultList();

	}

	@Override
	public void salvarCliente(ClienteRequest request) throws Exception {
		validaSalvarCliente(request);
		Cliente cliente = clienteRepository.findById(request.getId()).orElse(new Cliente());

		if (StringUtils.isNotBlank(request.getNome())) {
			cliente.setNome(request.getNome());
		}

		if (StringUtils.isNotBlank(request.getCpf())) {
			cliente.setCpf(request.getCpf());
		}

		if (Objects.nonNull(request.getDataCadastro())) {
			cliente.setDataCadastro(request.getDataCadastro());
		}

		clienteRepository.save(cliente);
	}

	@Override
	public void excluirCliente(ClienteRequest request) throws Exception {
		validaExcluirCliente(request);
		clienteRepository.deleteById(request.getId());
	}
	
	private void validaSalvarCliente(ClienteRequest request) throws Exception {
		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			return;
		} 

		if (StringUtils.isBlank(request.getNome())) {
			throw new RegistroIncompletoException("Informação sobre o nome do cliente não encontrada.");
		}

		if (StringUtils.isBlank(request.getCpf())) {
			throw new RegistroIncompletoException("Informação sobre o CPF do cliente não encontrada.");
		}

	}

	private void validaExcluirCliente(ClienteRequest request) throws Exception {
		if(Objects.nonNull(request.getId()) && request.getId() != 0) {
			// TODO Adicionar validação de caso cliente possua contatos/endereços/pets ou atendimentos e bloquear esssa remoção;
		} else {
			throw new RegistroIncompletoException("Informação sobre o id do cliente não encontrada.");
		}
	}

}
