package br.com.clothesshop.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clothesshop.api.model.Grupo;
import br.com.clothesshop.api.repository.GrupoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class GrupoControllerTest {

	private final String NOME = "Nome do Grupo";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Test
	public void deve_gravar_grupo_no_repositorio() {
		Grupo grupo = criarGrupo();
		Grupo usuarioSalvo = grupoRepository.save(grupo);
		assertThat(usuarioSalvo.getNome()).isEqualTo(NOME);
	}
	
	@Test
	public void deve_procurar_usuario_pelo_nome() {
		criarGrupo();
		Optional<Grupo> optional = grupoRepository.findByNome(NOME);
		assertThat(optional.isPresent()).isTrue();
	}
	
	private Grupo criarGrupo() {
		Grupo grupo = new Grupo();
		grupo.setNome(NOME);
		Grupo usuarioSalvo = grupoRepository.save(grupo);
		return usuarioSalvo;
	}
}
