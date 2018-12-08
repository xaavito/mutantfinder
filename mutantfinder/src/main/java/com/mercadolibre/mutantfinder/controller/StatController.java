package com.mercadolibre.mutantfinder.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutantfinder.dao.StatsRepository;
import com.mercadolibre.mutantfinder.entity.Stats;


@RestController
public class StatController {
	private final StatsRepository repository;

	StatController(StatsRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	@PostMapping("/stats")
	Stats stats() {
		return repository.findAll().get(0);
	}
}
