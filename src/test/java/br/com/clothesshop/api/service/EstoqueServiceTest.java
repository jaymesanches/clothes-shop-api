package br.com.clothesshop.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clothesshop.api.model.Estoque;
import br.com.clothesshop.api.repository.estoque.EstoqueRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstoqueServiceTest {

	@Autowired
	private EstoqueService estoqueService;

	@MockBean
	private EstoqueRepository estoqueRepository;

	private Estoque estoque;

	@Before
	public void setup() {
		estoque = new Estoque();
	}

	@Test
	public void deve_salvar_estoque_no_repositorio() throws Exception {
		estoqueService.create(estoque);
		verify(estoqueRepository).save(estoque);
	}

	@Test
	public void deve_encontrar_estoque_por_id() throws Exception {
		when(estoqueService.findOne(1)).thenReturn(estoque);
		Estoque estoqueEncontrado = estoqueService.findOne(1);
		assertThat(estoqueEncontrado).isNotNull();
		verify(estoqueRepository).getOne(1L);
	}
}
