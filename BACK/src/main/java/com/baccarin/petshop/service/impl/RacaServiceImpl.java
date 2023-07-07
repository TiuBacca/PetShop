package com.baccarin.petshop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baccarin.petshop.repository.RacaRepository;
import com.baccarin.petshop.service.RacaService;
import com.baccarin.petshop.vo.response.RacaResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RacaServiceImpl implements RacaService {

	private final RacaRepository racaRepository;

	@Override
	public List<RacaResponse> buscaListaRacas() {
		return racaRepository.buscaListaRacas();
	}

}
