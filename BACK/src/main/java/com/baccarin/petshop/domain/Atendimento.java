package com.baccarin.petshop.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "atendimento", schema = "petshop", uniqueConstraints = {})
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_pet")
	private Pet pet;

	@Column(name = "descricao", nullable = false, updatable = false)
	private String descricao;

	@Column(name = "valor", nullable = false, updatable = false)
	private BigDecimal valor;

	@Column(name = "dataAtendimento", updatable = false)
	private LocalDateTime dataAtendimento;

}
