package com.global.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.api.models.entity.Persona;
import com.global.api.models.service.IPersonaService;

@RestController
@RequestMapping("/api")
public class PersonaController {
	
	@Autowired
	private IPersonaService service;
	
	@GetMapping("/persona")
	public List<Persona> listarCliente(){
		return service.findAll();
	}
	
	@PostMapping("/persona")
	public ResponseEntity<?> guardarCliente(@Valid @RequestBody Persona persona, BindingResult result){
		Persona personaObj = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> resultError = result.getFieldErrors().stream().map(r-> "El campo '"+r.getField()+"' "+r.getDefaultMessage()).collect(Collectors.toList());
			response.put("error", resultError);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			personaObj = service.save(persona);
		} catch (DataAccessException e) {
			response.put("error", e.getMostSpecificCause().getMessage());
			response.put("mensaje", "Se produjo un error en la aplicacion");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Persona creada con exito.");
		response.put("persona", personaObj);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/persona/{id}")
	public ResponseEntity<?> actualizarCliente(@Valid @RequestBody Persona persona, BindingResult result, @PathVariable Long id){
		Persona personaFinal = null;
		Map<String, Object> response = new HashMap<>();
		Persona personaObj = service.findById(id);
		
		if(result.hasErrors()) {
			List<String> resultError = result.getFieldErrors().stream().map(r-> "El campo '"+r.getField()+"' "+r.getDefaultMessage()).collect(Collectors.toList());
			response.put("erros", resultError);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		if(personaObj == null) {
			response.put("mensaje", "No existe la persona con el id "+id);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			personaObj.setNombre(persona.getNombre());
			personaObj.setIdentificacion(persona.getIdentificacion());
			personaObj.setEdad(persona.getEdad());
			personaObj.setGenero(persona.getGenero());
			personaObj.setDireccion(persona.getDireccion());
			personaObj.setTelefono(persona.getTelefono());
				
			personaFinal = service.save(personaObj);
			
		} catch (DataAccessException e) {
			response.put("error", e.getMostSpecificCause().getMessage());
			response.put("mensaje", "Se produjo un error en la aplicacion");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Persona actualizada con exito.");
		response.put("persona", personaFinal);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/persona/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			service.deleteById(id);
		} catch (DataAccessException e) {
			response.put("error", e.getMostSpecificCause().getMessage());
			response.put("mensaje", "Se produjo un error en la aplicacion");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Persona eliminada con exito.");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}

}
