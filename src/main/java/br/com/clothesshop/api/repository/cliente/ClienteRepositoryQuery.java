package br.com.clothesshop.api.repository.cliente;

import java.util.List;

import br.com.clothesshop.api.model.Cliente;

public interface ClienteRepositoryQuery {
	List<Cliente> filtrar(Cliente clienteFiltro);
}
