package br.com.clothesshop.api.repository.estoque;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clothesshop.api.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
