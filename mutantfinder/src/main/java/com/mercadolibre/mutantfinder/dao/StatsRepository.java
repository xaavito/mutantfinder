package com.mercadolibre.mutantfinder.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mercadolibre.mutantfinder.entity.Stats;

/**
 * Repositorio de acuerdo a las especificaciones, solamente para las
 * Estadisticas
 * 
 * @author Javier Gonzalez
 *
 */
public interface StatsRepository extends MongoRepository<Stats, Long> {
	
}
