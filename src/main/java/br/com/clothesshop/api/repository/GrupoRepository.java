package br.com.clothesshop.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clothesshop.api.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	Optional<Grupo> findByNome(String nome);
}
