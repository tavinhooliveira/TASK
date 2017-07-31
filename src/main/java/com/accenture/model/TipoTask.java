package com.accenture.model;

public enum TipoTask {
	
	OUTROS(""),
	DCR("DCR"),
	SDN("SDN"),
	TIR("TIR"),
	MERGE("Merge"),
	Treinamento("Treinamento"),
	TAREFAS_ADM("Tarefas_ADM");
	
	private String descricao;
	
	TipoTask(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
