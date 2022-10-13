package com.global.api.models.service;

import java.util.List;

import com.global.api.models.entity.Persona;

public interface IPersonaService {
	List<Persona> findAll();
	Persona save(Persona cliente);
	Persona findById(Long id);
	void deleteById(Long id);
}
