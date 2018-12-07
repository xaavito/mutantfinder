package com.mercadolibre.mutantfinder.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "adn")
public class Adn {
	private @Id Long id;
	private String[] dna = new String[6];
	private int isMutant;

	Adn(String[] sec) {
		this.dna = sec;
		this.isMutant = 0;
	}

}
