package br.com.clothesshop.api.repository.produto;

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

import br.com.clothesshop.api.model.Produto;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Produto> filtrar(Produto produtoFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);
		
		Predicate[] predicates = criarRestricoes(produtoFiltro, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Produto> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	private Predicate[] criarRestricoes(Produto produtoFiltro, CriteriaBuilder builder,
			Root<Produto> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (StringUtils.hasText(produtoFiltro.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get("descricao")), "%" + produtoFiltro.getDescricao().toLowerCase() + "%"));
		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
