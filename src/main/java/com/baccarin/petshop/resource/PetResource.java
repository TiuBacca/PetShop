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
import com.baccarin.petshop.service.PetService;
import com.baccarin.petshop.vo.filtro.ContatoFiltro;
import com.baccarin.petshop.vo.filtro.PetFiltro;
import com.baccarin.petshop.vo.request.ContatoRequest;
import com.baccarin.petshop.vo.request.PetRequest;
import com.baccarin.petshop.vo.response.ObjetoGenericoResponse;
import com.baccarin.petshop.vo.response.PetReponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("pet")
@RequiredArgsConstructor
public class PetResource {

	private final PetService petService;

	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<PetReponse>> buscaListaPet(@RequestBody PetFiltro filtro) throws Exception {
		List<PetReponse> pets = petService.buscaListaPet(filtro);
		if (Objects.nonNull(pets) && !pets.isEmpty()) {
			return new ResponseEntity<List<PetReponse>>(pets, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "salvar")
	public ResponseEntity<ObjetoGenericoResponse> salvarPet(@RequestBody PetRequest request) {
		try {
			petService.salvarPet(request);
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Pet salvo com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroNaoEncontradoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao salvar pet."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "excluir")
	public ResponseEntity<ObjetoGenericoResponse> excluirPet(@RequestBody PetRequest request) {
		try {
			petService.excluirPet(request);
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Pet excluido com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroNaoEncontradoException e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ObjetoGenericoResponse>(new ObjetoGenericoResponse("Erro ao contato pet."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
