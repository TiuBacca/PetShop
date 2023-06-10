/**
 * 
 */
package com.baccarin.petshop.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.baccarin.petshop.domain.Usuario;
import com.baccarin.petshop.enums.PerfilUsuario;
import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.repository.UsuarioRepository;
import com.baccarin.petshop.service.UsuarioService;
import com.baccarin.petshop.vo.filtro.UsuarioFiltro;
import com.baccarin.petshop.vo.request.UsuarioRequest;
import com.baccarin.petshop.vo.response.UsuarioResponse;

/**
 * @author Guilherme
 *
 */
class UsuarioServiceImplTest {

	   @Mock
	    private UsuarioRepository usuarioRepository;

	    @InjectMocks
	    private UsuarioService usuarioService;

	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void salvarUsuario_ValidRequest_Success() throws Exception {
	        // Arrange
	        UsuarioRequest request = new UsuarioRequest();
	        request.setNome("John Doe");
	        request.setPerfil("ADMIN");

	        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario());

	        // Act
	        assertDoesNotThrow(() -> usuarioService.salvarUsuario(request));

	        // Assert
	        verify(usuarioRepository, times(1)).save(any(Usuario.class));
	    }

	    @Test
	    public void salvarUsuario_InvalidRequest_ThrowsException() {
	        // Arrange
	        UsuarioRequest request = new UsuarioRequest();
	        request.setPerfil("INVALID");

	        // Act & Assert
	        assertThrows(RegistroIncompletoException.class, () -> usuarioService.salvarUsuario(request));
	    }

	    @Test
	    public void excluirUsuario_ValidRequest_Success() throws Exception {
	        // Arrange
	        UsuarioRequest request = new UsuarioRequest();
	        request.setId(1L);

	        when(usuarioRepository.findById(request.getId())).thenReturn(Optional.of(new Usuario()));

	        // Act
	        assertDoesNotThrow(() -> usuarioService.excluirUsuario(request));

	        // Assert
	        verify(usuarioRepository, times(1)).deleteById(request.getId());
	    }

	    @Test
	    public void excluirUsuario_InvalidRequest_ThrowsException() {
	        // Arrange
	        UsuarioRequest request = new UsuarioRequest();

	        // Act & Assert
	        assertThrows(RegistroIncompletoException.class, () -> usuarioService.excluirUsuario(request));
	    }

	    @Test
	    public void buscaListaUsuarios_ValidFiltro_Success() throws Exception {
	        // Arrange
	        UsuarioFiltro filtro = new UsuarioFiltro();
	        filtro.setId(1L);

	        Usuario usuario1 = new Usuario();
	        usuario1.setId(1L);
	        usuario1.setNome("John Doe");
	        usuario1.setPerfil(PerfilUsuario.valueOf("ADMIN"));

	        List<Usuario> usuarios = Arrays.asList(usuario1);

	        when(usuarioRepository.findAll()).thenReturn(usuarios);

	        // Act
	        List<UsuarioResponse> response = usuarioService.buscaListaUsuarios(filtro);

	        // Assert
	        assertNotNull(response);
	        assertEquals(1, response.size());
	        assertEquals(usuario1.getId(), response.get(0).getId());
	        assertEquals(usuario1.getNome(), response.get(0).getNomeUsuario());
	        assertEquals(usuario1.getPerfil(), response.get(0).getPerfil());
	    }

	    @Test
	    public void buscaListaUsuarios_EmptyFiltro_Success() throws Exception {
	        // Arrange
	        UsuarioFiltro filtro = new UsuarioFiltro();

	        Usuario usuario1 = new Usuario();
	        usuario1.setId(1L);
	        usuario1.setNome("John Doe");
	        usuario1.setPerfil(PerfilUsuario.valueOf("ADMIN"));

	        Usuario usuario2 = new Usuario();
	        usuario2.setId(2L);
	        usuario2.setNome("Jane Smith");
	        usuario2.setPerfil(PerfilUsuario.valueOf("USER"));

	        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

	        when(usuarioRepository.findAll()).thenReturn(usuarios);

	        // Act
	        List<UsuarioResponse> response = usuarioService.buscaListaUsuarios(filtro);

	        // Assert
	        assertNotNull(response);
	        assertEquals(2, response.size());
	        assertEquals(usuario1.getId(), response.get(0).getId());
	        assertEquals(usuario1.getNome(), response.get(0).getNomeUsuario());
	        assertEquals(usuario1.getPerfil(), response.get(0).getPerfil());
	        assertEquals(usuario2.getId(), response.get(1).getId());
	        assertEquals(usuario2.getNome(), response.get(1).getNomeUsuario());
	        assertEquals(usuario2.getPerfil(), response.get(1).getPerfil());
	    }
}
