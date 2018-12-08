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
		this.count_human_dna = hum;
		this.count_mutant_dna = mut;
		this.ratio = rat;
	}
}
