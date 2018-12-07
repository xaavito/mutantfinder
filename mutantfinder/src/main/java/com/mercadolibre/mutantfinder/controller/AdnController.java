package com.mercadolibre.mutantfinder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutantfinder.dao.AdnRepository;
import com.mercadolibre.mutantfinder.entity.Adn;


@RestController
public class AdnController {
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
	Adn newEmployee(@RequestBody Adn newAdn) {
		return repository.save(newAdn);
	}

	// Single item
	/*
	@GetMapping("/employees/{id}")
	Employee one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@PutMapping("/employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

		return repository.findById(id).map(employee -> {
			employee.setName(newEmployee.getName());
			employee.setRole(newEmployee.getRole());
			return repository.save(employee);
		}).orElseGet(() -> {
			newEmployee.setId(id);
			return repository.save(newEmployee);
		});
	}

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
	*/
}
