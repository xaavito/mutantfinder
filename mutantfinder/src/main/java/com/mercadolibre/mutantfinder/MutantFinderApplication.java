package com.mercadolibre.mutantfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mercadolibre.mutantfinder.dao.AdnRepository;

@SpringBootApplication
@ComponentScan("com.mercadolibre.mutantfinder") //to scan packages mentioned
@EnableMongoRepositories(basePackages = "com.mercadolibre.mutantfinder")
public class MutantFinderApplication {
	//(exclude = { MongoAutoConfiguration.class })
	public static void main(String[] args) {
		SpringApplication.run(MutantFinderApplication.class, args);
	}
}
