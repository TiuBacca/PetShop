package com.baccarin.petshop.domain;

import java.math.BigDecimal;

import com.baccarin.petshop.enums.TipoContato;

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
@Table(name = "contato", schema = "petshop", uniqueConstraints = {})
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Column(name = "tag", nullable = false, updatable = true)
	private String tag;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false, updatable = true)
	private TipoContato tipo;

	@Column(name = "valor", nullable = false, updatable = true)
	private BigDecimal valor;

}
