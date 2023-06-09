package com.baccarin.petshop.vo.request;

import java.time.LocalDate;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.baccarin.petshop.domain.Cliente;
import com.baccarin.petshop.domain.Pet;
import com.baccarin.petshop.domain.Raca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetRequest {

	private Long id;
	private String nome;
	private Long idCliente;
	private Long idRaca;
	private LocalDate dataNascimento;
	
	
	
	public static Pet converToDomain(PetRequest request) {
		Pet pet = new Pet();

		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			pet.setId(request.getId());
		}
		
		if (Objects.nonNull(request.getIdCliente()) && request.getIdCliente() != 0) {
			pet.setCliente(Cliente.builder().id(request.getIdCliente()).build());
		}
		
		if (Objects.nonNull(request.getIdRaca()) && request.getIdRaca() != 0) {
			pet.setRaca(Raca.builder().id(request.getIdRaca()).build());
		}
		
		if (Objects.nonNull(request.getDataNascimento())) {
			pet.setDataNascimento(request.getDataNascimento());
		}
		
		if(StringUtils.isNotBlank(request.getNome())) {
			pet.setNome(request.getNome());
		}
		
		return pet;
	}


}
