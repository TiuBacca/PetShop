package com.baccarin.petshop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.domain.Contato;
import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.repository.ContatoRepository;
import com.baccarin.petshop.service.ContatoService;
import com.baccarin.petshop.vo.filtro.ContatoFiltro;
import com.baccarin.petshop.vo.request.ContatoRequest;
import com.baccarin.petshop.vo.response.ContatoResponse;

import jakarta.persistence.TypedQuery;

class ContatoServiceImplTest {

	   @Mock
	    private ContatoRepository contatoRepository;
	   
	    @Mock
	    private Util util;

	    @InjectMocks
	    private ContatoService contatoService;

	    @BeforeEach
	    void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void salvarContato_WithValidRequest_CallsRepositorySaveMethod() throws Exception {
	        // Arrange
	        ContatoRequest request = new ContatoRequest();
	        request.setId(1L);
	        request.setIdCliente(1L);
	        request.setTipo("email");
	        when(contatoRepository.findById(eq(request.getId()))).thenReturn(Optional.empty());
	        Contato savedContato = new Contato();
	        when(contatoRepository.save(any(Contato.class))).thenReturn(savedContato);

	        // Act
	        contatoService.salvarContato(request);

	        // Assert
	        verify(contatoRepository, times(1)).findById(eq(request.getId()));
	        verify(contatoRepository, times(1)).save(any(Contato.class));
	        assertEquals(savedContato, request);
	    }

	    @Test
	    void salvarContato_WithEmptyIdCliente_ThrowsRegistroIncompletoException() {
	        // Arrange
	        ContatoRequest request = new ContatoRequest();
	        request.setTipo("email");

	        // Act & Assert
	        assertThrows(RegistroIncompletoException.class, () -> contatoService.salvarContato(request));
	    }

	    @Test
	    void salvarContato_WithEmptyTipo_ThrowsRegistroIncompletoException() {
	        // Arrange
	        ContatoRequest request = new ContatoRequest();
	        request.setIdCliente(1L);

	        // Act & Assert
	        assertThrows(RegistroIncompletoException.class, () -> contatoService.salvarContato(request));
	    }

	    @Test
	    void excluirContato_WithValidRequest_CallsRepositoryDeleteByIdMethod() throws Exception {
	        // Arrange
	        ContatoRequest request = new ContatoRequest();
	        request.setId(1L);
	        when(contatoRepository.findById(eq(request.getId()))).thenReturn(Optional.of(new Contato()));

	        // Act
	        contatoService.excluirContato(request);

	        // Assert
	        verify(contatoRepository, times(1)).findById(eq(request.getId()));
	        verify(contatoRepository, times(1)).deleteById(eq(request.getId()));
	    }

	    @Test
	    void excluirContato_WithEmptyId_ThrowsRegistroIncompletoException() {
	        // Arrange
	        ContatoRequest request = new ContatoRequest();

	        // Act & Assert
	        assertThrows(RegistroIncompletoException.class, () -> contatoService.excluirContato(request));
	    }

	    @Test
	    void buscaListaContatos_WithValidFiltro_ReturnsContatoResponseList() throws Exception {
	        // Arrange
	        ContatoFiltro filtro = new ContatoFiltro();
	        filtro.setTag("tag");
	        TypedQuery<ContatoResponse> query = mock(TypedQuery.class);
	        List<ContatoResponse> expectedResponse = new ArrayList<>();
	        when(util.getEntityManager().createQuery(anyString(), eq(ContatoResponse.class))).thenReturn(query);
	        when(query.getResultList()).thenReturn(expectedResponse);

	        // Act
	        List<ContatoResponse> result = contatoService.buscaListaContatos(filtro);

	        // Assert
	        verify(util.getEntityManager(), times(1)).createQuery(anyString(), eq(ContatoResponse.class));
	        verify(query, times(1)).getResultList();
	        assertEquals(expectedResponse, result);
	    }

}
