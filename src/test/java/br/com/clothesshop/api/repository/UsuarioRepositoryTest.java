package br.com.clothesshop.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clothesshop.api.model.Usuario;
import br.com.clothesshop.api.repository.usuario.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class UsuarioRepositoryTest {

	private final String NOME = "Jayme Sanches";
	private final String EMAIL = "jaymesanches@yahoo.com.br";
	private final String SENHA = "12345";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	public void deve_gravar_usuario_no_repositorio() {
		Usuario usuarioSalvo = salvarUsuario();
		assertThat(usuarioSalvo.getNome()).isEqualTo(NOME);
	}

	@Test(expected = ConstraintViolationException.class)
	public void deve_lancar_excecao_ao_salvar_usuario_sem_email() {
		Usuario usuario = novoUsuario();
		usuario.setEmail(null);
		usuarioRepository.save(usuario);
		entityManager.flush();
	}
	
	@Test
	public void deve_procurar_usuario_pelo_nome() {
		salvarUsuario();
		Optional<Usuario> optional = usuarioRepository.findByNome(NOME);
		assertThat(optional.isPresent()).isTrue();
	}
	
	@Test
	public void deve_procurar_usuario_pelo_email() {
		salvarUsuario();
		Optional<Usuario> optional = usuarioRepository.findByEmail(EMAIL);
		assertThat(optional.isPresent()).isTrue();
	}
	
	private Usuario salvarUsuario() {
		Usuario usuario = novoUsuario();
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		entityManager.flush();
		return usuarioSalvo;
	}

	private Usuario novoUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome(NOME);
		usuario.setEmail(EMAIL);
		usuario.setSenha(SENHA);
		return usuario;
	}
}
