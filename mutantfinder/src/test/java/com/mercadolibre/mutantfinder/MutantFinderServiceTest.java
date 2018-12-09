package com.mercadolibre.mutantfinder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class MutantFinderServiceTest {
	@Autowired
	private WebTestClient webClient;

	@Test
	public void estadisticasSinNAda() {
		//this.webClient.get().uri("/stats").exchange().expectStatus().isOk()
		//.json("{\"ratio\":\"0\"}");

		this.webClient.get().uri("/stats").exchange().expectBody().json("{\"ratio\":\"0\"}");
		//returnResult.getResponseBodyContent();
		
		//this.webClient.get().uri("/stats").exchange().expectBody().toString();
	}
}
