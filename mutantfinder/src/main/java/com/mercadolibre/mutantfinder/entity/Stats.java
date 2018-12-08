package com.mercadolibre.mutantfinder.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.ConcurrentHashMultiset;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Document(collection = "stats")
@Data
@NoArgsConstructor
public class Stats {
	private @Id String id;
	private int count_mutant_dna;
	private int count_human_dna;
	private String ratio;

	public Stats(int mut, int hum, String rat) {
		this.setCount_human_dna(hum);
		this.setCount_mutant_dna(mut);
		this.setRatio(rat);
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
