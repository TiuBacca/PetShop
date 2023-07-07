package com.baccarin.petshop.resource;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.service.AtendimentoService;
import com.baccarin.petshop.vo.filtro.AtendimentoFiltro;
import com.baccarin.petshop.vo.request.AtendimentoRequest;
import com.baccarin.petshop.vo.response.AtendimentoResponse;
import com.baccarin.petshop.vo.response.ObjetoGenericoResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("atendimento")
@RequiredArgsConstructor
@Api(tags = "Atendimento")
public class AtendimentoResource {

	private final AtendimentoService atendimentoService;

	@GetMapping(path = "buscaLista")
	@ApiOperation("Buscar lista de atendimentos")
	public ResponseEntity<List<AtendimentoResponse>> buscarListaAtendimento(@RequestBody AtendimentoFiltro filtro)
			throws Exception {
		List<AtendimentoResponse> atendimentos = atendimentoService.buscarListaAtendimento(filtro);
		if (Objects.nonNull(atendimentos)) {
			return new ResponseEntity<List<AtendimentoResponse>>(atendimentos, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "salvar")
    @ApiOperation("Salvar atendimento")
	public ResponseEntity<ObjetoGenericoResponse> salvarAtendimento(@RequestBody AtendimentoRequest request) {
		try {

			atendimentoService.salvarAtendimento(request);
			return new ResponseEntity<ObjetoGenericoResponse>(
					new ObjetoGenericoResponse("Atendimento salvo com sucesso."), HttpStatus.OK);
		} catch (RegistroIncompletoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao salvar atendimento."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
