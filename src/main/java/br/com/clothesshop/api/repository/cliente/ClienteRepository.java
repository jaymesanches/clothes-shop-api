package br.com.clothesshop.api.repository.cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clothesshop.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery {
	Optional<Cliente> findByEmail(String email);
	Optional<Cliente> findByDocumento(String documento);
}
