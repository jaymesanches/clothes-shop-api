package br.com.clothesshop.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clothesshop.api.model.Produto;
import br.com.clothesshop.api.repository.produto.ProdutoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ProdutoRepositoryTest {

	private final long CODIGO = 12345;
	private final String DESCRICAO = "Produto1";
	private final String COR = "Verde";
	private final BigDecimal VALOR = BigDecimal.TEN;
	private final BigDecimal MARGEM_PADRAO = BigDecimal.TEN;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	public void deve_gravar_usuario_no_repositorio() {
		Produto produtoSalvo = salvarProduto();
		assertThat(produtoSalvo.getDescricao()).isEqualTo(DESCRICAO);
	}

	@Test(expected = ConstraintViolationException.class)
	public void deve_lancar_excecao_ao_salvar_produto_sem_descricao() {
		Produto produto = novoProduto();
		produto.setDescricao(null);
		produtoRepository.save(produto);
		entityManager.flush();
	}
	
	@Test
	public void deve_procurar_produtos_por_parte_da_descricao() {
		salvarProduto();
		Produto filtro = new Produto();
		filtro.setDescricao("prod");
		List<Produto> produtos = produtoRepository.filtrar(filtro);
		assertThat(produtos.size()).isGreaterThan(0);
	}
	
	@Test
	public void deve_procurar_produto_por_codigo() {
		salvarProduto();
		Optional<Produto> optional = produtoRepository.findByCodigo(CODIGO);
		assertThat(optional.isPresent()).isTrue();
	}
	
	private Produto salvarProduto() {
		Produto produto = novoProduto();
		Produto produtoSalvo = produtoRepository.save(produto);
		entityManager.flush();
		return produtoSalvo;
	}

	private Produto novoProduto() {
		Produto produto = new Produto();
		produto.setCodigo(CODIGO);
		produto.setDescricao(DESCRICAO);
		produto.setCor(COR);
		produto.setDetalhes("asd");
		produto.setValorCusto(VALOR);
		produto.setMargemLucroPadrao(MARGEM_PADRAO);
		return produto;
	}
}
