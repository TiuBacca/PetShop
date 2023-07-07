package com.baccarin.petshop.enums;

public enum PerfilUsuario {

	CLIENTE("Cliente"), ADM("Adm");

	private String descricao;

	PerfilUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
