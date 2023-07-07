package com.baccarin.petshop.vo.request;

import org.apache.commons.lang3.StringUtils;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.domain.Cliente;
import com.baccarin.petshop.domain.Usuario;
import com.baccarin.petshop.enums.PerfilUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

	private Long id;
	private String nome;
	private Long idCliente;
	private String perfil;
	private String senha;
	
	
	public static Usuario converteToDomain(UsuarioRequest request) {
		Usuario usuario = new Usuario();
		
		if(Util.validaDiferenteNullAndDiferenteZero(request.getId())) {
			usuario.setId(request.getId());
		}
		
		if(StringUtils.isNotBlank(request.getNome())) {
			usuario.setNome(request.getNome());
		}
		
		if(Util.validaDiferenteNullAndDiferenteZero(request.getIdCliente())) {
			usuario.setCliente(Cliente.builder().id(request.getIdCliente()).build());
		}
		
		if(StringUtils.isNotBlank(request.getPerfil())) {
			try {
				PerfilUsuario perfil = PerfilUsuario.valueOf(request.getPerfil());
				usuario.setPerfil(perfil);
			} catch (Exception e ) {
				
			}
		}
		
		if(StringUtils.isNotBlank(request.getSenha())) {
			
		}
		
		return usuario;
	}

}
