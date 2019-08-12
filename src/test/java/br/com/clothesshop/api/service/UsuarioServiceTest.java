package br.com.clothesshop.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clothesshop.api.model.Usuario;
import br.com.clothesshop.api.repository.usuario.UsuarioRepository;
import br.com.clothesshop.api.service.exception.EmailJaCadastradoException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {
	
	private final String NOME = "Jayme Sanches";
	private final String EMAIL = "jaymesanches@yahoo.com.br";

	@Autowired
	private UsuarioService usuarioService;
	
	@MockBean
	private UsuarioRepository usuarioRepository;

	private Usuario usuario;

	@Before
	public void setup() {
		usuario = new Usuario();
		usuario.setNome(NOME);
		usuario.setEmail(EMAIL);
	}

	@Test
	public void deve_salvar_usuario_no_repositorio() throws Exception {
		usuarioService.create(usuario);
		verify(usuarioRepository).save(usuario);
	}
	
	@Test(expected = EmailJaCadastradoException.class)
	public void nao_deve_gravar_email_repetido() throws Exception {
		when(usuarioService.findByEmail(EMAIL)).thenReturn(Optional.of(usuario));
		usuarioService.create(usuario);
	}
	
	@Test
	public void deve_encontrar_usuario_por_id() throws Exception {
		when(usuarioService.findOne(1L)).thenReturn(usuario);
		Usuario usuarioEncontrado = usuarioService.findOne(1L);
		assertThat(usuarioEncontrado.getNome()).isEqualTo(NOME);
	}
}
