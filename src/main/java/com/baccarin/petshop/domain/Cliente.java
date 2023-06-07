package com.baccarin.petshop.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente", schema = "petshop", uniqueConstraints = {})
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome",unique = true, nullable = false, updatable = true)
	private String nome;

	@Column(name = "cpf", nullable = false, updatable = true)
	private String cpf;

	@Column(name = "data_cadastro", nullable = false, updatable = true)
	private LocalDateTime dataCadastro;
	

}
