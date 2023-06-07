package com.baccarin.petshop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "endereco", schema = "petshop", uniqueConstraints = {})
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@Column(name = "logradouro", nullable = false, updatable = true)
	private String logradouro;

	@Column(name = "cidade", nullable = false, updatable = true)
	private String cidade;

	@Column(name = "bairro", updatable = true)
	private String bairro;

	@Column(name = "complemento", updatable = true)
	private String complemento;

	@Column(name = "tag", updatable = true)
	private String tag;

}
