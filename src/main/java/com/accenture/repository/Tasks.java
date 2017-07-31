package com.accenture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.model.Task;

public interface Tasks extends JpaRepository<Task, Long> {
	
	public List<Task> findByDescricaoContaining(String descricao);

}
