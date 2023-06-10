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
import com.baccarin.petshop.exceptions.RegistroNaoEncontradoException;
import com.baccarin.petshop.service.ContatoService;
import com.baccarin.petshop.vo.filtro.ClienteFiltro;
import com.baccarin.petshop.vo.filtro.ContatoFiltro;
import com.baccarin.petshop.vo.request.ContatoRequest;
import com.baccarin.petshop.vo.response.ClienteResponse;
import com.baccarin.petshop.vo.response.ContatoResponse;
import com.baccarin.petshop.vo.response.ObjetoGenericoResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("contato")
@RequiredArgsConstructor
@Api(tags = "Contato")
public class ContatoResource {

	private ContatoService contatoService;
	
	
	@PostMapping(path = "buscaLista/byFiltro")
	@ApiOperation("Buscar lista de contatos")
	public ResponseEntity<List<ContatoResponse>> buscaListaContatos(@RequestBody ContatoFiltro filtro)
			throws Exception {
		List<ContatoResponse> contatos = contatoService.buscaListaContatos(filtro);
		if (Objects.nonNull(contatos) && !contatos.isEmpty()) {
			return new ResponseEntity<List<ContatoResponse>>(contatos, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "salvar")
	@ApiOperation("Salvar contato")
	public ResponseEntity<ObjetoGenericoResponse> salvarContato(@RequestBody ContatoRequest request) {
		try {
			contatoService.salvarContato(request);
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Contato salvo com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroNaoEncontradoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao salvar contato."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "excluir")
	@ApiOperation("Excluir contato")
	public ResponseEntity<ObjetoGenericoResponse> excluirContato(@RequestBody ContatoRequest request) {
		try {
			contatoService.excluirContato(request);
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Contato excluido com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroNaoEncontradoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao contato cliente."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
