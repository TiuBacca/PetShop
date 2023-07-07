package com.baccarin.petshop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.domain.Endereco;
import com.baccarin.petshop.repository.EnderecoRepository;
import com.baccarin.petshop.service.EnderecoService;
import com.baccarin.petshop.vo.filtro.EnderecoFiltro;
import com.baccarin.petshop.vo.request.EnderecoRequest;
import com.baccarin.petshop.vo.response.EnderecoResponse;

import jakarta.persistence.TypedQuery;

class EnderecoServiceImplTest {

    @Mock
    private EnderecoRepository enderecoRepository;
    
    @Mock
    private Util util;
    
    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    public void buscaListaEnderecos_WithValidFiltro_ReturnsList() throws Exception {
        // Arrange
        EnderecoFiltro filtro = new EnderecoFiltro();
        filtro.setId(1L);
        filtro.setLogradouro("Rua");
        filtro.setCidades(Arrays.asList("São Paulo", "Rio de Janeiro"));
        filtro.setBairro("Centro");
        filtro.setComplemento("Apartamento");
        filtro.setTag("Residencial");

        List<EnderecoResponse> expectedResponse = new ArrayList<>();
        // Add expected response objects to the list

        TypedQuery<EnderecoResponse> query = mock(TypedQuery.class);
        when(util.getEntityManager().createQuery(anyString(), eq(EnderecoResponse.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedResponse);

        // Act
        List<EnderecoResponse> result = enderecoService.buscaListaEnderecos(filtro);

        // Assert
        verify(util.getEntityManager(), times(1)).createQuery(anyString(), eq(EnderecoResponse.class));
        verify(query, times(1)).getResultList();
        assertEquals(expectedResponse, result);
    }

    @Test
    public void salvarEndereco_WithValidRequest_CallsSaveMethod() throws Exception {
        // Arrange
        EnderecoRequest request = new EnderecoRequest();
        // Set request properties

        doNothing().when(enderecoRepository).save(any(Endereco.class));

        // Act
        enderecoService.salvarEndereco(request);

        // Assert
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    public void excluirEndereco_WithValidRequest_CallsDeleteByIdMethod() throws Exception {
        // Arrange
        EnderecoRequest request = new EnderecoRequest();
        request.setId(1L);

        doNothing().when(enderecoRepository).deleteById(anyLong());

        // Act
        enderecoService.excluirEndereco(request);

        // Assert
        verify(enderecoRepository, times(1)).deleteById(anyLong());
    }
}
