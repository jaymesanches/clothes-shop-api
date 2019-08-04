package br.com.clothesshop.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clothesshop.api.model.Usuario;
import br.com.clothesshop.api.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class UsuarioControllerTest {

	private final String NOME = "Jayme Sanches";
	private final String EMAIL = "jaymesanches@yahoo.com.br";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void deve_gravar_usuario_no_repositorio() {
		Usuario usuario = criarUsuario();
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		assertThat(usuarioSalvo.getNome()).isEqualTo(NOME);
	}
	
	@Test
	public void deve_procurar_usuario_pelo_nome() {
		criarUsuario();
		Optional<Usuario> optional = usuarioRepository.findByNome(NOME);
		assertThat(optional.isPresent()).isTrue();
	}
	
	@Test
	public void deve_procurar_usuario_pelo_email() {
		criarUsuario();
		Optional<Usuario> optional = usuarioRepository.findByEmail(EMAIL);
		assertThat(optional.isPresent()).isTrue();
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome(NOME);
		usuario.setEmail(EMAIL);
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return usuarioSalvo;
	}
}
