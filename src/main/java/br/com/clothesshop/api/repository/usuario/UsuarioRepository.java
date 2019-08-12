package br.com.clothesshop.api.repository.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clothesshop.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {

	Optional<Usuario> findByNome(String nome);
	Optional<Usuario> findByEmail(String email);

}
