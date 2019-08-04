package br.com.clothesshop.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@Sql(value = "/create_database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/delete_database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public abstract class ClothesShopApiApplicationTests {

	@Value("${local.server.port}")
	protected int porta;

	@Before
	public void setup() throws Exception {
		RestAssured.port = porta;
	}

	@Test
	public void contextLoads() {
		System.out.println("PORTA: " + porta);
	}

}
