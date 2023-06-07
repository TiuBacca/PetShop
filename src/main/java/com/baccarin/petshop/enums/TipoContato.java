package com.baccarin.petshop.enums;

public enum TipoContato {

	EMAIL("E-mail"), TELEFONE("Telefone");

	private String descricao;

	TipoContato(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
