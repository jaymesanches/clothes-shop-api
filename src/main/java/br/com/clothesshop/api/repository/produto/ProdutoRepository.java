package br.com.clothesshop.api.repository.produto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clothesshop.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQuery {

	Optional<Produto> findByCodigo(long codigo);
	
}
