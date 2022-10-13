package com.global.api.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="persona")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "[0-9]+",message = "Solo permite valores numericos")
	@NotEmpty(message = "El campo no puede ser vacio")
	@Column(unique=true, length = 10, name = "identificacion")
	private String identificacion;
	
	@NotEmpty(message = "El campo no puede ser vacio")
	@Column(name = "nombre")
	private String nombre;
	
	@NotEmpty(message = "El campo no puede ser vacio")
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "edad")
	private int edad;
	
	@NotEmpty(message = "El campo no puede ser vacio")
	@Column(name = "direccion")
	private String direccion;
	
	@NotEmpty(message = "El campo no puede ser vacio")
	@Column(name = "telefono")
	private String telefono;
}
