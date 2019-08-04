package br.com.clothesshop.api.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import br.com.clothesshop.api.ClothesShopApiApplicationTests;
import br.com.clothesshop.api.model.Usuario;
import io.restassured.http.ContentType;

public class UsuarioControllerTest extends ClothesShopApiApplicationTests {

	private final String NOME = "Isabela";

	@Test
	public void deve_retornar_todos_usuarios() {
		// @formatter:off
		
		when()
			.get("/api/usuarios")
		.then()
			.log().body().and()
			.statusCode(HttpStatus.OK.value());
		
		List<Usuario> usuarios =
				given()
				  .contentType(ContentType.JSON)
				.when()
				  .get("/api/usuarios")
				.then()
				.extract().response().body().path("");
		
		assertThat(usuarios.size()).isEqualTo(2);
		
		// @formatter:on
	}

	@Test
	public void deve_retornar_usuario_por_id() {
		// @formatter:off

		when()
  	    	.get("/api/usuarios/1")
  	    .then()
  	    	.log().body()
  	    .and()
  	    	.assertThat().body("nome", equalTo(NOME));
					
		
		// @formatter:on
	}
}
