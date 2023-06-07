package com.baccarin.petshop.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.petshop.service.ContatoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("contato")
@RequiredArgsConstructor
public class ContatoResource {

	private ContatoService contatoService;
}
