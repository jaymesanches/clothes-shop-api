package br.com.clothesshop.api.repository.usuario;

import java.util.List;

import br.com.clothesshop.api.model.Usuario;

public interface UsuarioRepositoryQuery {
	List<Usuario> filtrar(Usuario usuarioFiltro);
}
