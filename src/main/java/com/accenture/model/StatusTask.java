package com.accenture.model;

public enum StatusTask {
	
	OPEN("Open"),
	ON_HOLD("On_Hold"),
	REJECTED("Rejected"),
	MERGED("Merge"),
	DELIVERY("Delivery"),
	CLOSED("Closed");
	
	private String descricao;
	
	StatusTask (String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
