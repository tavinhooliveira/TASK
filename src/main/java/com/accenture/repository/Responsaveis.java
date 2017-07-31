package com.accenture.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.model.Responsavel;

public interface Responsaveis extends JpaRepository<Responsavel, Long>{
	
	public List<Responsaveis> findByNomeContaining(String nome);
	
	public Optional<Responsaveis> findByNomeIgnoreCase(String nome);

}
