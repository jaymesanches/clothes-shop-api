package br.com.clothesshop.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clothesshop.api.model.Produto;
import br.com.clothesshop.api.repository.produto.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdutoServiceTest {

	private final long CODIGO = 12345;
	private final String DESCRICAO = "Produto1";
	private final String COR = "Verde";
	private final BigDecimal VALOR = BigDecimal.TEN;
	private final BigDecimal MARGEM_PADRAO = BigDecimal.TEN;

	@Autowired
	private ProdutoService produtoService;

	@MockBean
	private ProdutoRepository produtoRepository;

	private Produto produto;

	@Before
	public void setup() {
		produto = new Produto();
		produto.setCodigo(CODIGO);
		produto.setDescricao(DESCRICAO);
		produto.setCor(COR);
		produto.setValorCusto(VALOR);
		produto.setMargemLucroPadrao(MARGEM_PADRAO);;
	}

	@Test
	public void deve_salvar_produto_no_repositorio() throws Exception {
		produtoService.create(produto);
		verify(produtoRepository).save(produto);
	}

	@Test
	public void deve_encontrar_produto_por_id() throws Exception {
		when(produtoService.findOne(1)).thenReturn(produto);
		Produto produtoEncontrado = produtoService.findOne(1);
		assertThat(produtoEncontrado.getDescricao()).isEqualTo(DESCRICAO);
	}
	
	@Test
	public void deve_encontrar_produto_por_codigo() throws Exception {
		when(produtoService.consultarPorCodigo(CODIGO)).thenReturn(Optional.of(produto));
		Optional<Produto> produtoEncontrado = produtoService.consultarPorCodigo(CODIGO);
		verify(produtoRepository).findByCodigo(CODIGO);
		assertThat(produtoEncontrado.get().getDescricao()).isEqualTo(DESCRICAO);
	}
}
