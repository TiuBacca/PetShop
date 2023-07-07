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
import com.baccarin.petshop.service.ClienteService;
import com.baccarin.petshop.vo.filtro.ClienteFiltro;
import com.baccarin.petshop.vo.request.ClienteRequest;
import com.baccarin.petshop.vo.response.ClienteResponse;
import com.baccarin.petshop.vo.response.ObjetoGenericoResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cliente")
@RequiredArgsConstructor
@Api(tags = "Cliente")
public class ClienteResource {

	private final ClienteService clienteService;

	@PostMapping(path = "buscaLista/byFiltro")
	@ApiOperation("Buscar lista de clientes")
	public ResponseEntity<List<ClienteResponse>> buscaListaClientes(@RequestBody ClienteFiltro filtro)
			throws Exception {
		List<ClienteResponse> clientes = clienteService.buscaListaClientes(filtro);
		if (Objects.nonNull(clientes) && !clientes.isEmpty()) {
			return new ResponseEntity<List<ClienteResponse>>(clientes, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "salvar")
	@ApiOperation("Salvar cliente")
	public ResponseEntity<ObjetoGenericoResponse> salvarCliente(@RequestBody ClienteRequest request) {
		try {
			clienteService.salvarCliente(request);
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Cliente salvo com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao salvar cliente."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "excluir")
	@ApiOperation("Excluir cliente")
	public ResponseEntity<ObjetoGenericoResponse> excluirCliente(@RequestBody ClienteRequest request) {
		try {
			clienteService.excluirCliente(request);
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Cliente excluido com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao excluido cliente."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
