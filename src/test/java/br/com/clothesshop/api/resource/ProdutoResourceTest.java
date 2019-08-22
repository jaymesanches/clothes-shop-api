package br.com.clothesshop.api.resource;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.clothesshop.api.ClothesShopApiApplicationTests;
import br.com.clothesshop.api.model.Produto;
import io.restassured.http.ContentType;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProdutoResourceTest extends ClothesShopApiApplicationTests {

	private final long CODIGO = 12345;
	private final String DESCRICAO = "Produto1";

	@Test
	public void deve_retornar_todos_produtos() {
		// @formatter:off
		
		when()
			.get("/api/produtos")
		.then()
			.statusCode(HttpStatus.OK.value());
		
		List<Produto> produtos =
				given()
				  .contentType(ContentType.JSON)
				.when()
				  .get("/api/produtos")
				.then()
				.extract().response().body().path("");
		
		assertThat(produtos.size()).isEqualTo(2);
		
		// @formatter:on
	}

	@Test
	public void deve_retornar_produto_por_id() {
		// @formatter:off

		when()
  	    	.get("/api/produtos/1")
  	    .then()
  	    	.log().body()
  	    .and()
  	    	.assertThat().body("descricao", equalTo(DESCRICAO));
		
		// @formatter:on
	}

	@Test
	public void deve_salvar_um_produto_no_sistema() {
		Produto produto = new Produto();
		produto.setCodigo(CODIGO);
		produto.setDescricao(DESCRICAO);

		// @formatter:off

		given()
			.request()
			.headers("Accept", ContentType.ANY)
			.headers("Content-type", ContentType.JSON)
			.body(produto)
		.when()
			.post("/api/produtos")
		.then()
			.log().status()
		.and()
			.statusCode(HttpStatus.CREATED.value());
			
		// @formatter:on
	}
	
	@Test
	public void deve_salvar_um_produto_com_estoque_no_sistema() {
		Produto produto = new Produto();
		produto.setCodigo(123333);
		produto.setDescricao(DESCRICAO+"123");
		produto.setTamanhoG(10);
		produto.setTamanhoM(5);
		
		// @formatter:off
		
		given()
			.request()
			.headers("Accept", ContentType.ANY)
			.headers("Content-type", ContentType.JSON)
			.body(produto)
		.when()
			.post("/api/produtos")
		.then()
			.log().status()
		.and()
			.statusCode(HttpStatus.CREATED.value());
		
		// @formatter:on
	}

	@Test
	public void deve_alterar_um_produto_no_sistema() {
		final String NOVA_DESCRICAO = "Novo Produto";
		Produto produto = new Produto();
		produto.setDescricao(NOVA_DESCRICAO);

		// @formatter:off

		given()
			.request()
			.headers("Accept", ContentType.ANY)
			.headers("Content-type", ContentType.JSON)
			.body(produto)
		.when()
			.put("/api/produtos/1")
		.then()
			.log().status()
		.and()
			.statusCode(HttpStatus.OK.value())
		.and()
			.body("descricao", equalTo(NOVA_DESCRICAO))
		;
			
		// @formatter:on
	}

	@Test
	public void deve_retornar_usuario_nao_encontrado_quando_pesquisado_por_nome_inexistente() {
		Produto produto = new Produto();
		produto.setDescricao("Inexistente");

		// @formatter:off

		given()
			.request()
			.headers("Accept", ContentType.ANY)
			.headers("Content-type", ContentType.JSON)
			.body(produto)
		.when()
			.get("/api/produtos")
		.then()
//			.log().status()
			
		;
		
		// @formatter:on		
	}
}
