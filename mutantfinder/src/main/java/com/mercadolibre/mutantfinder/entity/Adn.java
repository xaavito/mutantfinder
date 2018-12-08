package com.mercadolibre.mutantfinder.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Document(collection = "adn")
@Data
@NoArgsConstructor
public class Adn {
	private @Id  String id;
	private String[] dna = new String[6];
	private int isMutant;

	public Adn(String[] dna) {
		this.setDna(dna);
		this.setIsMutant(0);
	}

	public int getIsMutant() {
		return isMutant;
	}

	public void setIsMutant(int isMutant) {
		this.isMutant = isMutant;
	}

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}
}
