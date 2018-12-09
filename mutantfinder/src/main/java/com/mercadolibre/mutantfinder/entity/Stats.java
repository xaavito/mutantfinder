package com.mercadolibre.mutantfinder.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Clase para la representacion de datos de la BD de las estadisticas, las
 * creamos una vez y las vamos actualizando
 * 
 * @author Javier Gonzalez
 *
 */
@Document(collection = "stats")
@Data
public class Stats {
	private @Id String id;
	private int count_mutant_dna;
	private int count_human_dna;
	private String ratio;

	public Stats(int count_mutant_dna, int count_human_dna, String ratio) {
		this.count_mutant_dna = count_mutant_dna;
		this.count_human_dna = count_human_dna;
		this.ratio = ratio;
	}

	public Stats() {
	}

	public int getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(int count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	public int getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(int count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
}
