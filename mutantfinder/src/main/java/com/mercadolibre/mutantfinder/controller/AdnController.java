package com.mercadolibre.mutantfinder.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutantfinder.dao.AdnRepository;
import com.mercadolibre.mutantfinder.entity.Adn;
import com.mercadolibre.mutantfinder.excepction.IMutantFinderService;

/**
 * 1 de los 2 controllers de entradas. Solo para el chequeo de si es mutante o
 * no.
 * 
 * @author Javier Gonzalez
 *
 */
@RestController
public class AdnController {

	private static final Logger logger = LoggerFactory.getLogger(AdnController.class);

	@Autowired
	private IMutantFinderService service;

	private final AdnRepository repository;

	AdnController(AdnRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	@GetMapping("/all")
	List<Adn> all() {
		logger.info("Getting all calls to service");
		return repository.findAll();
	}

	/**
	 * Metodo de entrada para el chequeo de un ADN es mutante o humano
	 * 
	 * @param newAdn
	 * @return
	 */
	@PostMapping("/mutant")
	// @ResponseStatus(HttpStatus.OK)
	ResponseEntity mutant(@RequestBody Adn newAdn) {
		logger.info("Checking for new muttants");
		boolean result = service.isMutant(newAdn.getDna());
		newAdn.setIsMutant(result ? 1 : 0);
		repository.save(newAdn);
		if (newAdn.getIsMutant() == 1) {
			logger.info("OK, is mutant");
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			logger.info("NO OK, is a filthy human!");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
}
