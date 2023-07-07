/**
 * 
 */
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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.baccarin.petshop.Util;
import com.baccarin.petshop.domain.Pet;
import com.baccarin.petshop.repository.PetRepository;
import com.baccarin.petshop.service.PetService;
import com.baccarin.petshop.vo.filtro.PetFiltro;
import com.baccarin.petshop.vo.request.ClienteRequest;
import com.baccarin.petshop.vo.request.PetRequest;
import com.baccarin.petshop.vo.response.PetReponse;
import com.baccarin.petshop.vo.response.RacaResponse;

import jakarta.persistence.TypedQuery;

/**
 * @author Guilherme
 *
 */

@RunWith(MockitoJUnitRunner.class)
class PetServiceImplTest {

	   @Mock
	    private PetRepository petRepository;
	    
	    @Mock
	    private Util util;
	    
	    @InjectMocks
	    private PetService petService;

	    @Test
	    public void buscaListaPet_WithValidFiltro_ReturnsList() throws Exception {
	        // Arrange
	        PetFiltro filtro = new PetFiltro();
	        filtro.setId(1L);
	        filtro.setNome("Max");
	        filtro.setClientes(Arrays.asList(new ClienteRequest(1L, "John Doe"), new ClienteRequest(2L, "Jane Smith")));
	        filtro.setRacas(Arrays.asList(new RacaResponse(1L, "Labrador Retriever"), new RacaResponse(2L, "Golden Retriever")));

	        List<PetReponse> expectedResponse = new ArrayList<>();
	        // Add expected response objects to the list

	        TypedQuery<PetReponse> query = mock(TypedQuery.class);
	        when(util.getEntityManager().createQuery(anyString(), eq(PetReponse.class))).thenReturn(query);
	        when(query.getResultList()).thenReturn(expectedResponse);

	        // Act
	        List<PetReponse> result = petService.buscaListaPet(filtro);

	        // Assert
	        verify(util.getEntityManager(), times(1)).createQuery(anyString(), eq(PetReponse.class));
	        verify(query, times(1)).getResultList();
	        assertEquals(expectedResponse, result);
	    }

	    @Test
	    public void salvarPet_WithValidRequest_CallsSaveMethod() throws Exception {
	        // Arrange
	        PetRequest request = new PetRequest();
	        // Set request properties

	        doNothing().when(petRepository).save(any(Pet.class));

	        // Act
	        petService.salvarPet(request);

	        // Assert
	        verify(petRepository, times(1)).save(any(Pet.class));
	    }

	    @Test
	    public void excluirPet_WithValidRequest_CallsDeleteByIdMethod() throws Exception {
	        // Arrange
	        PetRequest request = new PetRequest();
	        request.setId(1L);

	        doNothing().when(petRepository).deleteById(anyLong());

	        // Act
	        petService.excluirPet(request);

	        // Assert
	        verify(petRepository, times(1)).deleteById(anyLong());
	    }
}
