package br.com.clothesshop.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clothesshop.api.model.Estoque;
import br.com.clothesshop.api.model.Produto;
import br.com.clothesshop.api.repository.estoque.EstoqueRepository;
import br.com.clothesshop.api.repository.produto.ProdutoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class EstoqueRepositoryTest {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	private final long CODIGO = 12345;
	private final String DESCRICAO = "Produto1";
	private final String COR = "Verde";
	private final BigDecimal VALOR = BigDecimal.TEN;
	private final BigDecimal MARGEM_PADRAO = BigDecimal.TEN;
	
	@Test
	public void deve_gravar_estoque_no_repositorio() {
		Estoque estoqueSalvo = salvarEstoque();
		assertThat(estoqueSalvo.getId()).isGreaterThan(0);
	}

	@Test(expected = ConstraintViolationException.class)
	public void deve_lancar_excecao_ao_salvar_estoque_sem_produto() {
		Estoque estoque = novoEstoque();
		estoque.setProduto(null);
		estoqueRepository.save(estoque);
		entityManager.flush();
	}
	
	private Estoque salvarEstoque() {
		Estoque estoque = novoEstoque();
		Estoque estoqueSalvo = estoqueRepository.save(estoque);
		entityManager.flush();
		return estoqueSalvo;
	}

	private Estoque novoEstoque() {
		Produto produto = salvarProduto();
		Estoque estoque = new Estoque();
		estoque.setProduto(produto);
		estoque.setTamanhoP(1);
		estoque.setTamanhoPP(1);
		estoque.setTamanhoM(1);
		estoque.setTamanhoG(1);
		estoque.setTamanhoGG(1);
		estoque.setTamanhoXXG(1);
		estoque.setTamanhoU(1);
		return estoque;
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
