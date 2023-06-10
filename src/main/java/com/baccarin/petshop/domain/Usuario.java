package com.baccarin.petshop.domain;

import com.baccarin.petshop.enums.PerfilUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", schema = "petshop", uniqueConstraints = {})
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome",unique = true, nullable = false, updatable = true)
	private String nome;

	@Column(name = "senha", nullable = false, updatable = true)
	private String senha;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false, updatable = true)
	private PerfilUsuario perfil;
}
