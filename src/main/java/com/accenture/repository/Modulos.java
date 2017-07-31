package com.accenture.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.model.Modulo;

public interface Modulos extends JpaRepository<Modulo, Long> {
	
public List<Modulos> findByNomeContaining (String nome);
	
	public Optional<Modulos> findByNomeIgnoreCase (String nome);
		

}

