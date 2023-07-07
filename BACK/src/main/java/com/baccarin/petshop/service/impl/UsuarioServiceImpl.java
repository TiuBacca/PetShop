package com.baccarin.petshop.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.enums.PerfilUsuario;
import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.exceptions.RegistroNaoEncontradoException;
import com.baccarin.petshop.repository.UsuarioRepository;
import com.baccarin.petshop.service.UsuarioService;
import com.baccarin.petshop.vo.filtro.UsuarioFiltro;
import com.baccarin.petshop.vo.request.ClienteRequest;
import com.baccarin.petshop.vo.request.UsuarioRequest;
import com.baccarin.petshop.vo.response.UsuarioResponse;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final Util util;

	@Override
	public void salvarUsuario(UsuarioRequest request) throws Exception {
		validaSalvarUsuario(request);
		usuarioRepository.save(UsuarioRequest.converteToDomain(request));
	}

	@Override
	public void excluirUsuario(UsuarioRequest request) throws Exception {
		validaExcluirUsuario(request);
		usuarioRepository.deleteById(request.getId());
	}

	@Override
	public List<UsuarioResponse> buscaListaUsuarios(UsuarioFiltro filtro) throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append(
				" select new com.baccarin.petshop.vo.response.UsuarioResponse (u.id, u.cliente.id, u.cliente.nome, u.nome, u.perfil ) from Usuario u \n"
						+ " WHERE u.id > 0 ");

		if (Util.validaDiferenteNullAndDiferenteZero(filtro.getId())) {
			sb.append(" AND c.id = :id");
		}

		if (Objects.nonNull(filtro.getClientes()) && !filtro.getClientes().isEmpty()) {
			sb.append(" AND c.cliente.id in (:idClientes) \n ");
		}

		if (Objects.nonNull(filtro.getTipos()) && !filtro.getTipos().isEmpty()) {
			sb.append(" AND c.tipo in (:tipos) \n ");
		}

		TypedQuery<UsuarioResponse> query = util.getEntityManager().createQuery(sb.toString(), UsuarioResponse.class);

		if (Util.validaDiferenteNullAndDiferenteZero(filtro.getId())) {
			query.setParameter("id", filtro.getId());
		}

		if (Objects.nonNull(filtro.getClientes()) && !filtro.getClientes().isEmpty()) {
			List<Long> idClientes = filtro.getClientes().stream().map(ClienteRequest::getId)
					.collect(Collectors.toList());
			if (!idClientes.isEmpty()) {
				query.setParameter("idClientes", idClientes);
			}
		}

		if (Objects.nonNull(filtro.getTipos()) && !filtro.getTipos().isEmpty()) {
			query.setParameter("tipos", filtro.getTipos());

		}

		return query.getResultList();
	}

	private void validaSalvarUsuario(UsuarioRequest request) throws Exception {
		if (Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			usuarioRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
					"Usuário equivalente ao código informado não foi encontrado."));
		} else {

			if (StringUtils.isBlank(request.getNome())) {
				throw new RegistroIncompletoException(
						"Para salvar um novo usuário, é necessário informar o nome dele.");
			}

			if (StringUtils.isBlank(request.getPerfil())) {
				throw new RegistroIncompletoException(
						"Para salvar um novo usuário, é necessário informar o tipo de perfil dele.");
			} else {
				try {
					PerfilUsuario.valueOf(request.getPerfil());
				} catch (Exception e) {
					throw new RegistroIncompletoException(
							"Para salvar um novo usuário, é necessário informar o tipo de perfil válido.");
				}
			}

		}
	}

	private void validaExcluirUsuario(UsuarioRequest request) throws Exception {
		if (Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			usuarioRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException(
					"Usuario equivalente ao código informado não foi encontrado."));
		}

		throw new RegistroIncompletoException("Para remover o contato, é necessário informar o seu código.");
	}

}
