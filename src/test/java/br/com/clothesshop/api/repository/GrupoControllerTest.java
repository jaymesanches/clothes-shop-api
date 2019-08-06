package br.com.clothesshop.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.persistence.EntityManager;

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
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	public void deve_gravar_grupo_no_repositorio() {
		Grupo grupoSalvo = salvarGrupo();
		assertThat(grupoSalvo.getNome()).isEqualTo(NOME);
	}
	
	@Test
	public void deve_procurar_usuario_pelo_nome() {
		salvarGrupo();
		Optional<Grupo> optional = grupoRepository.findByNome(NOME);
		assertThat(optional.isPresent()).isTrue();
	}
	
	private Grupo salvarGrupo() {
		Grupo grupo = novoGrupo();
		Grupo usuarioSalvo = grupoRepository.save(grupo);
		entityManager.flush();
		return usuarioSalvo;
	}

	private Grupo novoGrupo() {
		Grupo grupo = new Grupo();
		grupo.setNome(NOME);
		return grupo;
	}
}
