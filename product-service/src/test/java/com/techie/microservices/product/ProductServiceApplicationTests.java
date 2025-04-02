package com.techie.microservices.product;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import static org.hamcrest.Matchers.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	static {
		mongoDBContainer.start();
	}

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateProduct() {
		String productJson = """
                {
                    "name": "Iphone 15",
                    "description": "Iphone 15 is a smartphone from Apple",
                    "price": 899
                }
                """;

		RestAssured.given()
				.contentType("application/json")
				.body(productJson)
				.when()
				.post("/api/products")
				.then()
				.statusCode(201)
				.body("id", notNullValue())
				.body("name", equalTo("Iphone 15"))
				.body("description", equalTo("Iphone 15 is a smartphone from Apple"))
				.body("price", equalTo(899));
	}
}
