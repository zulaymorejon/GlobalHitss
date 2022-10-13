package com.global.api.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.global.api.models.entity.Persona;
import com.global.api.models.repository.IPersonaRepository;


@Service
public class PersonaService implements IPersonaService {

	@Autowired
	private IPersonaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll(){
		return (List<Persona>) repository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Persona findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Persona save(Persona persona) {
		return repository.save(persona);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
