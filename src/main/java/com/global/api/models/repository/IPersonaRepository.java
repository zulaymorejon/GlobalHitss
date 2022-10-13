package com.global.api.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.global.api.models.entity.Persona;

@Repository
public interface IPersonaRepository extends PagingAndSortingRepository<Persona, Long> {

}
