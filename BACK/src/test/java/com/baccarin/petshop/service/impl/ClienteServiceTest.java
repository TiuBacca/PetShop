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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.domain.Cliente;
import com.baccarin.petshop.exceptions.RegistroIncompletoException;
import com.baccarin.petshop.repository.ClienteRepository;
import com.baccarin.petshop.service.ClienteService;
import com.baccarin.petshop.vo.filtro.ClienteFiltro;
import com.baccarin.petshop.vo.request.ClienteRequest;
import com.baccarin.petshop.vo.response.ClienteResponse;

import jakarta.persistence.TypedQuery;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private Util util;
    
    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscaListaClientes_WithValidFiltro_ReturnsClienteResponseList() throws Exception {
        // Arrange
        ClienteFiltro filtro = new ClienteFiltro();
        filtro.setNome("John");
        TypedQuery<ClienteResponse> query = mock(TypedQuery.class);
        List<ClienteResponse> expectedResponse = new ArrayList<>();
        when(util.getEntityManager().createQuery(anyString(), eq(ClienteResponse.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedResponse);

        // Act
        List<ClienteResponse> result = clienteService.buscaListaClientes(filtro);

        // Assert
        verify(util.getEntityManager(), times(1)).createQuery(anyString(), eq(ClienteResponse.class));
        verify(query, times(1)).getResultList();
        assertEquals(expectedResponse, result);
    }

    @Test
    void salvarCliente_WithValidRequest_CallsRepositorySaveMethod() throws Exception {
        // Arrange
        ClienteRequest request = new ClienteRequest();
        request.setId(1L);
        request.setNome("John Doe");
        request.setCpf("1234567890");
        request.setDataCadastro(LocalDateTime.now());
        when(clienteRepository.findById(eq(request.getId()))).thenReturn(Optional.empty());
        Cliente savedCliente = new Cliente();
        when(clienteRepository.save(any(Cliente.class))).thenReturn(savedCliente);

        // Act
        clienteService.salvarCliente(request);

        // Assert
        verify(clienteRepository, times(1)).findById(eq(request.getId()));
        verify(clienteRepository, times(1)).save(any(Cliente.class));
        assertEquals(savedCliente, request);
    }

    @Test
    void salvarCliente_WithEmptyNome_ThrowsRegistroIncompletoException() {
        // Arrange
        ClienteRequest request = new ClienteRequest();
        request.setCpf("1234567890");

        // Act & Assert
        assertThrows(RegistroIncompletoException.class, () -> clienteService.salvarCliente(request));
    }

    @Test
    void salvarCliente_WithEmptyCpf_ThrowsRegistroIncompletoException() {
        // Arrange
        ClienteRequest request = new ClienteRequest();
        request.setNome("John Doe");

        // Act & Assert
        assertThrows(RegistroIncompletoException.class, () -> clienteService.salvarCliente(request));
    }

    @Test
    void excluirCliente_WithValidRequest_CallsRepositoryDeleteByIdMethod() throws Exception {
        // Arrange
        ClienteRequest request = new ClienteRequest();
        request.setId(1L);
        when(clienteRepository.findById(eq(request.getId()))).thenReturn(Optional.of(new Cliente()));

        // Act
        clienteService.excluirCliente(request);

        // Assert
        verify(clienteRepository, times(1)).findById(eq(request.getId()));
        verify(clienteRepository, times(1)).deleteById(eq(request.getId()));
    }

    @Test
    void excluirCliente_WithEmptyId_ThrowsRegistroIncompletoException() {
        // Arrange
        ClienteRequest request = new ClienteRequest();

        // Act & Assert
        assertThrows(RegistroIncompletoException.class, () -> clienteService.excluirCliente(request));
    }
}