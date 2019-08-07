package br.com.clothesshop.api.repository.produto;

import java.util.List;

import br.com.clothesshop.api.model.Produto;

public interface ProdutoRepositoryQuery {
	List<Produto> filtrar(Produto produtoFiltro);
}
