package com.baccarin.petshop.resource;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.service.UsuarioService;
import com.baccarin.petshop.vo.filtro.UsuarioFiltro;
import com.baccarin.petshop.vo.request.UsuarioRequest;
import com.baccarin.petshop.vo.response.ClienteResponse;
import com.baccarin.petshop.vo.response.ObjetoGenericoResponse;
import com.baccarin.petshop.vo.response.UsuarioResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioResource {
	
	private final UsuarioService usuarioService;
	
	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<UsuarioResponse>> buscaListaUsuarios(@RequestBody UsuarioFiltro filtro)
			throws Exception {
		List<UsuarioResponse> usuarios = usuarioService.buscaListaUsuarios(filtro);
		if (Objects.nonNull(usuarios) && !usuarios.isEmpty()) {
			return new ResponseEntity<List<UsuarioResponse>>(usuarios, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "salvar")
	public ResponseEntity<ObjetoGenericoResponse> salvarUsuario(@RequestBody UsuarioRequest request) {
		try {
			usuarioService.salvarUsuario(request);
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Usuário salvo com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao salvar usuário."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "excluir")
	public ResponseEntity<ObjetoGenericoResponse> excluirUsuario(@RequestBody UsuarioRequest request) {
		try {
			usuarioService.excluirUsuario(request);
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Usuário excluido com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao excluido usuário."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
