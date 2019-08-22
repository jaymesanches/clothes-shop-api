package br.com.clothesshop.api.resource;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import br.com.clothesshop.api.ClothesShopApiApplicationTests;
import br.com.clothesshop.api.model.Grupo;
import io.restassured.http.ContentType;

public class GrupoResourceTest extends ClothesShopApiApplicationTests {

	private final String NOME = "Grupo 1";

	@Test
	public void deve_retornar_todos_os_grupos() {
		// @formatter:off
		
		when()
			.get("/api/grupos")
		.then()
			.statusCode(HttpStatus.OK.value());
		
		List<Grupo> grupos =
				given()
				  .contentType(ContentType.JSON)
				.when()
				  .get("/api/grupos")
				.then()
				.extract().response().body().path("");
		
		assertThat(grupos.size()).isEqualTo(2);
		
		// @formatter:on
	}

	@Test
	public void deve_retornar_grupo_por_id() {
		// @formatter:off

		when()
  	    	.get("/api/grupos/1")
  	    .then()
  	    	.assertThat().body("nome", equalTo(NOME));
		
		// @formatter:on
	}

	@Test
	public void deve_salvar_um_grupo_no_sistema() {
		Grupo grupo = new Grupo();
		grupo.setNome(NOME);

		// @formatter:off

		given()
			.request()
			.headers("Accept", ContentType.ANY)
			.headers("Content-type", ContentType.JSON)
			.body(grupo)
		.when()
		.post("/api/grupos")
		.then()
			.statusCode(HttpStatus.CREATED.value());
			
		// @formatter:on
	}
	
	@Test
	public void deve_alterar_um_grupo_no_sistema() {
		final String NOVO_NOME = "Novo Nome";
		Grupo grupo = new Grupo();
		grupo.setNome(NOVO_NOME);

		// @formatter:off

		given()
			.request()
			.headers("Accept", ContentType.ANY)
			.headers("Content-type", ContentType.JSON)
			.body(grupo)
		.when()
		.put("/api/grupos/1")
		.then()
			.log().body()
		.and()
			.statusCode(HttpStatus.OK.value())
		.and()
			.body("nome", equalTo(NOVO_NOME))
		;
			
		// @formatter:on
	}
}
