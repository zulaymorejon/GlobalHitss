package com.global.api;

import java.util.Arrays;
import java.util.List;

import com.global.api.models.entity.Persona;

public class DatosPrueba {
	
	public static final List<Persona> personas = Arrays.asList(new Persona(1L, "0929008316", "Zulay", "Femenino", 31, "Los Tulipnaes", "0995681"),
			new Persona(2L, "0929008324", "Zaidy", "Femenino", 26, "Los Tulipnaes", "09955681"));
	
	public static final Persona personaResponse= new Persona(1L, "0929008316", "Zulay", "Femenino", 31, "Los Tulipnaes", "0995681");
	
	public static Persona personaRequest() {
		Persona persona = new Persona();
		return persona.builder()
		.identificacion("0929008616")
		.nombre("Zulay")
		.genero("Femenino")
		.edad(31)
		.direccion("Los Tulipanes")
		.telefono("123654")
		.build();
		
	}
}
