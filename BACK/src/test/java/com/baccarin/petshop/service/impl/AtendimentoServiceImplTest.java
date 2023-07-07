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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.domain.Atendimento;
import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.exceptions.RegistroNaoEncontradoException;
import com.baccarin.petshop.repository.AtendimentoRepository;
import com.baccarin.petshop.repository.PetRepository;
import com.baccarin.petshop.service.AtendimentoService;
import com.baccarin.petshop.vo.filtro.AtendimentoFiltro;
import com.baccarin.petshop.vo.request.AtendimentoRequest;
import com.baccarin.petshop.vo.request.PetRequest;
import com.baccarin.petshop.vo.response.AtendimentoResponse;

import jakarta.persistence.TypedQuery;

class AtendimentoServiceImplTest {

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Util util;
    
    @InjectMocks
    private AtendimentoService atendimentoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    void buscarListaAtendimento_WithValidFiltro_ReturnsAtendimentoResponseList() throws Exception {
        // Arrange
        AtendimentoFiltro filtro = new AtendimentoFiltro();
        filtro.setId(1L);
        List<PetRequest> pets = new ArrayList<>();
        PetRequest pet = new PetRequest();
        pet.setId(2L);
        pets.add(pet);
        filtro.setPets(pets);
        TypedQuery<AtendimentoResponse> query = mock(TypedQuery.class);
        List<AtendimentoResponse> expectedResponse = new ArrayList<>();
        when(util.getEntityManager().createQuery(anyString(), eq(AtendimentoResponse.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedResponse);

        // Act
        List<AtendimentoResponse> result = atendimentoService.buscarListaAtendimento(filtro);

        // Assert
        verify(util.getEntityManager(), times(1)).createQuery(anyString(), eq(AtendimentoResponse.class));
        verify(query, times(1)).getResultList();
        assertEquals(expectedResponse, result);
    }

    @Test
    void salvarAtendimento_WithValidRequest_CallsRepositorySaveMethod() throws Exception {
        // Arrange
        AtendimentoRequest request = new AtendimentoRequest();
        request.setId(1L);
        request.setPet(new PetRequest());
        request.getPet().setId(2L);
        request.setValor(new BigDecimal(100.00));
        when(atendimentoRepository.findById(eq(request.getId()))).thenReturn(Optional.empty());
        when(petRepository.findById(eq(request.getPet().getId()))).thenReturn(Optional.empty());

        // Act
        atendimentoService.salvarAtendimento(request);

        // Assert
        verify(atendimentoRepository, times(1)).findById(eq(request.getId()));
        verify(atendimentoRepository, times(1)).save(any(Atendimento.class));
        verify(petRepository, times(1)).findById(eq(request.getPet().getId()));
    }

    @Test
    void salvarAtendimento_WithNullValue_ThrowsRegistroIncompletoException() {
        // Arrange
        AtendimentoRequest request = new AtendimentoRequest();
        request.setValor(null);

        // Act & Assert
        assertThrows(RegistroIncompletoException.class, () -> atendimentoService.salvarAtendimento(request));
    }

    @Test
    void salvarAtendimento_WithExistingId_ThrowsRegistroNaoEncontradoException() {
        // Arrange
        AtendimentoRequest request = new AtendimentoRequest();
        request.setId(1L);
        when(atendimentoRepository.findById(eq(request.getId()))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RegistroNaoEncontradoException.class, () -> atendimentoService.salvarAtendimento(request));
    }
}
