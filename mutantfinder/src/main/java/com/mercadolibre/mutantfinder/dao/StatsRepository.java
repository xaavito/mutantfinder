package com.mercadolibre.mutantfinder.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mercadolibre.mutantfinder.entity.Adn;
import com.mercadolibre.mutantfinder.entity.Stats;

public interface StatsRepository extends MongoRepository<Stats, Long>{

}
