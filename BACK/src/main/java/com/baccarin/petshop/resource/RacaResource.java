package com.baccarin.petshop.resource;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.petshop.service.RacaService;
import com.baccarin.petshop.vo.response.RacaResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("raca")
@RequiredArgsConstructor
@Api(tags = "Raça")
public class RacaResource {

	private final RacaService racaService;

	@GetMapping(path = "buscaLista")
	@ApiOperation("Buscar lista de raças")
	public ResponseEntity<List<RacaResponse>> buscaListaRacas() throws Exception {
		List<RacaResponse> racas = racaService.buscaListaRacas();
		if (Objects.nonNull(racas)) {
			return new ResponseEntity<List<RacaResponse>>(racas, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
