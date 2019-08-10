package br.com.clothesshop.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clothesshop.api.model.Produto;
import br.com.clothesshop.api.repository.produto.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto create(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto findOne(long id) {
		return produtoRepository.getOne(id);
	}

	public Optional<Produto> consultarPorCodigo(long codigo) throws Exception {
		return produtoRepository.findByCodigo(codigo);
	}

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto update(Long id, Produto produto) {
		return produtoRepository.save(produto);
	}

}
