package com.global.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.global.api.models.entity.Persona;
import com.global.api.models.repository.IPersonaRepository;
import com.global.api.models.service.PersonaService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {GlobalApplicationTests.class})
@SpringBootTest
class GlobalApplicationTests {

	@InjectMocks
	private PersonaService service;
		
	@Mock
	private IPersonaRepository respository;
	
	@Test
	void findAllTotalRegistrosTest() {
		when(respository.findAll()).thenReturn(DatosPrueba.personas);
		
		List<Persona> persona = service.findAll();
		assertEquals(2, persona.size());
		assertNotNull(persona);
		verify(respository,times(1)).findAll();
	}
	
	@Test
	void findAllVacioTest() {		
		List<Persona> persona = Collections.emptyList();
		when(respository.findAll()).thenReturn(persona);
		List<Persona> personaVacio = service.findAll();
		assertEquals(0, personaVacio.size());
		assertFalse(personaVacio==null);
		verify(respository,times(1)).findAll();
	}
	
	@Test
	void saveTest() {		
		
		  when(respository.save(any(Persona.class))).thenReturn(DatosPrueba.personaResponse); 
		  Persona persona = service.save(DatosPrueba.personaRequest());
		  assertEquals("Zulay", persona.getNombre()); 
		  assertEquals("0929008316", persona.getIdentificacion());
		  verify(respository,times(1)).save(any(Persona.class));
		 
	}
	
	@Test
	void deleteTest() {		
		respository.findById(anyLong());
		service.deleteById(1L);

		verify(respository,times(1)).deleteById(anyLong());
	}

}
