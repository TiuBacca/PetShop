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
import com.baccarin.petshop.service.EnderecoService;
import com.baccarin.petshop.vo.filtro.EnderecoFiltro;
import com.baccarin.petshop.vo.request.EnderecoRequest;
import com.baccarin.petshop.vo.response.EnderecoResponse;
import com.baccarin.petshop.vo.response.ObjetoGenericoResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("endereco")
@RequiredArgsConstructor
public class EnderecoResource {

	private final EnderecoService enderecoService;
	
	
	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<EnderecoResponse>> buscaListaEnderecos(@RequestBody EnderecoFiltro filtro)
			throws Exception {
		List<EnderecoResponse> enderecos = enderecoService.buscaListaEnderecos(filtro);
		if (Objects.nonNull(enderecos) && !enderecos.isEmpty()) {
			return new ResponseEntity<List<EnderecoResponse>>(enderecos, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "salvar")
	public ResponseEntity<ObjetoGenericoResponse> salvarEndereco(@RequestBody EnderecoRequest request) {
		try {
			enderecoService.salvarEndereco(request);
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Endereço salvo com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroNaoEncontradoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao salvar endereço."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "excluir")
	public ResponseEntity<ObjetoGenericoResponse> excluirEndereco(@RequestBody EnderecoRequest request) {
		try {
			enderecoService.excluirEndereco(request);
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Endereço excluido com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroNaoEncontradoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao contato endereço."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
