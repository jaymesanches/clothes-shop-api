package br.com.clothesshop.api.repository.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.com.clothesshop.api.model.Cliente;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cliente> filtrar(Cliente usuarioFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		
		Predicate[] predicates = criarRestricoes(usuarioFiltro, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Cliente> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	private Predicate[] criarRestricoes(Cliente usuarioFiltro, CriteriaBuilder builder,
			Root<Cliente> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (StringUtils.hasText(usuarioFiltro.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + usuarioFiltro.getNome().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
