package br.com.salao.entidades.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Categoria {

	MANICURE("Manicure"),
	DEPILACAO("Depilação"),
	SOBRANCELHA("Sobrancelhas"),
	CABELO("Cabelo");
	
	private String cat;
	
	Categoria() {
	}
	@JsonValue
	public String getCat() {
		return cat;
	}

	private Categoria(String categoria) {
		this.cat = categoria;
	}
}
