package br.com.clothesshop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clothesshop.api.model.Estoque;
import br.com.clothesshop.api.repository.estoque.EstoqueRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;

	public void create(Estoque produto) {
		estoqueRepository.save(produto);
	}

	public Estoque findOne(long id) {
		return estoqueRepository.getOne(id);
	}
}
