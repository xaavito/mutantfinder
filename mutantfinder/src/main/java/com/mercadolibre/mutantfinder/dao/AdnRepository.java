package com.mercadolibre.mutantfinder.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mercadolibre.mutantfinder.entity.Adn;

public interface AdnRepository extends MongoRepository<Adn, Long>{

}
