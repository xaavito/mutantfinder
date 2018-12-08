package com.mercadolibre.mutantfinder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutantfinder.dao.StatsRepository;
import com.mercadolibre.mutantfinder.entity.Stats;

@RestController
public class StatController {
	private static final Logger logger = LoggerFactory.getLogger(StatController.class);

	private final StatsRepository repository;

	StatController(StatsRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
	@GetMapping("/stats")
	Stats stats() {
		logger.info("CALLING STATISTICS!");
		return repository.findAll().get(0);
	}
}
