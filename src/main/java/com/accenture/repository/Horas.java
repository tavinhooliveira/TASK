package com.accenture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.model.Hora;

public interface Horas extends JpaRepository<Hora,Long> {
	

}
