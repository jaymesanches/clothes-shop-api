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

	@Test
	public void deve_salvar_um_usuario_no_sistema() {
		Usuario usuario = new Usuario();
		usuario.setNome("Jose da Silva");
		usuario.setEmail("jose@silva.com.br");

		// @formatter:off

		given()
			.request()
			.headers("Accept", ContentType.ANY)
			.headers("Content-type", ContentType.JSON)
			.body(usuario)
		.when()
		.post("/api/usuarios")
		.then()
			.statusCode(HttpStatus.CREATED.value());
			
		// @formatter:on
	}
	
	@Test
	public void deve_alterar_um_usuario_no_sistema() {
		final String NOVO_NOME = "Novo nome";
		Usuario usuario = new Usuario();
		usuario.setNome(NOVO_NOME);

		// @formatter:off

		given()
			.request()
			.headers("Accept", ContentType.ANY)
			.headers("Content-type", ContentType.JSON)
			.body(usuario)
		.when()
		.put("/api/usuarios/1")
		.then()
			.log().body()
		.and()
			.statusCode(HttpStatus.OK.value())
		.and()
			.body("nome", equalTo(NOVO_NOME))
		;
			
		// @formatter:on
	}
	
	@Test
	public void deve_retornar_usuario_nao_encontrado_quando_pesquisado_por_nome_inexistente() {
		Usuario usuario = new Usuario();
		usuario.setNome("nome inexistente");

		// @formatter:off

		given()
			.request()
			.headers("Accept", ContentType.ANY)
			.headers("Content-type", ContentType.JSON)
			.body(usuario)
		.when()
			.get("/usuarios")
		.then()
			.log().body()
		.and()
			.log().status()
			
		;
	}
}
