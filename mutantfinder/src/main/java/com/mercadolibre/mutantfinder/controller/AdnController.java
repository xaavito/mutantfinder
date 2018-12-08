package com.mercadolibre.mutantfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutantfinder.dao.AdnRepository;
import com.mercadolibre.mutantfinder.entity.Adn;
import com.mercadolibre.mutantfinder.excepction.IFooService;

@RestController
public class AdnController {
	
	//pasar a servicio o usar esto??
	@Autowired
	private IFooService service;
	
	private final AdnRepository repository;

	AdnController(AdnRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	@GetMapping
	List<Adn> all() {
		return repository.findAll();
	}

	@PostMapping("/mutant")
	// @ResponseStatus(HttpStatus.OK)
	ResponseEntity mutant(@RequestBody Adn newAdn) {
		repository.save(newAdn);
		if (newAdn.getIsMutant() == 0) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
}
