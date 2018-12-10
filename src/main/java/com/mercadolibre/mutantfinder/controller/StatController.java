package com.mercadolibre.mutantfinder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutantfinder.dao.StatsRepository;
import com.mercadolibre.mutantfinder.entity.Stats;

/**
 * 1 de los 2 controllers de entradas. Solo para estadisticas que las gaurdamos en la BD
 * @author Javier Gonzalez
 *
 */
@RestController
public class StatController {
	private static final Logger logger = LoggerFactory.getLogger(StatController.class);

	private final StatsRepository repository;

	StatController(StatsRepository repository) {
		this.repository = repository;
	}

	/**
	 * Metodo de entrada para la obtencion de estadisticas generales
	 * @return
	 */
	@GetMapping("/stats")
	Stats stats() {
		logger.info("CALLING STATISTICS!");
		if (repository.findAll() != null && repository.findAll().size() > 0) {
			return repository.findAll().get(0);
		}
		else {
			return new Stats(0, 0, "0");
		}
	}
}
