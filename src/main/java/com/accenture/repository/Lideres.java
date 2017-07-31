package com.accenture.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.model.Lider;

public interface Lideres extends JpaRepository<Lider,Long> {
	
	public List<Lideres> findByNomeContaining (String nome);
	
	public Optional<Lideres> findByNomeIgnoreCase (String nome);
		

}
