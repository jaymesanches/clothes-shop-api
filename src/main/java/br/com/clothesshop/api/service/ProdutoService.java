package br.com.clothesshop.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clothesshop.api.model.Produto;
import br.com.clothesshop.api.repository.produto.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public void create(Produto produto) {
		produtoRepository.save(produto);
	}

	public Produto findOne(long id) {
		return produtoRepository.getOne(id);
	}

	public Optional<Produto> consultarPorCodigo(long codigo) throws Exception {
		return produtoRepository.findByCodigo(codigo);
	}

}
