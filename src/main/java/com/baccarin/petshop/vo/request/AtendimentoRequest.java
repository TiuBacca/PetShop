package com.baccarin.petshop.vo.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.domain.Atendimento;
import com.baccarin.petshop.domain.Pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtendimentoRequest {

	private Long id;
	private PetRequest pet;
	private String descricao;
	private BigDecimal valor;
	private LocalDateTime dataAtendimento;


	public static Atendimento converteToDomain(AtendimentoRequest request) {
		Atendimento atendimento = new Atendimento();
		
		if(Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			atendimento.setId(request.getId());
		}
		
		if(Objects.nonNull(request.getPet())) {
			if(Util.validaDiferenteNullAndDiferenteZero(request.getPet().getId())) {
				atendimento.setPet(Pet.builder().id(request.getPet().getId()).build());
			}
		}
		
		if(StringUtils.isNotBlank(request.getDescricao())) {
			atendimento.setDescricao(request.getDescricao());
		}
		
		if(Objects.nonNull(request.getDataAtendimento())) {
			atendimento.setDataAtendimento(request.getDataAtendimento());
		} else {
			atendimento.setDataAtendimento(LocalDateTime.now());
		}
		
		if(Objects.nonNull(request.getValor())) {
			atendimento.setValor(request.getValor());
		}
		
		return atendimento;
		
	}
}
