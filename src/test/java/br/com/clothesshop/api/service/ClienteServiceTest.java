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

import br.com.clothesshop.api.model.Grupo;
import br.com.clothesshop.api.repository.grupo.GrupoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {
	
	private final String NOME = "GRUPO";

	@Autowired
	private GrupoService grupoService;
	
	@MockBean
	private GrupoRepository grupoRepository;

	private Grupo grupo;

	@Before
	public void setup() {
		grupo = new Grupo();
		grupo.setNome(NOME);
	}

	@Test
	public void deve_salvar_grupo_no_repositorio() throws Exception {
		grupoService.create(grupo);
		verify(grupoRepository).save(grupo);
	}
	
	@Test
	public void deve_encontrar_usuario_por_id() throws Exception {
		when(grupoService.findOne(1L)).thenReturn(grupo);
		Grupo grupoEncontrado = grupoService.findOne(1L);
		assertThat(grupoEncontrado.getNome()).isEqualTo(NOME);
	}
}
