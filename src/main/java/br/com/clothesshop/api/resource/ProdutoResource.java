package br.com.clothesshop.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.clothesshop.api.model.Produto;
import br.com.clothesshop.api.service.ProdutoService;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoResource {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<Produto> findAll(HttpServletResponse response) {
		List<Produto> usuarios = produtoService.findAll();
		eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
		return usuarios;
	}

	@GetMapping(value = "/{id}")
	public Produto findById(@PathVariable("id") Long id, HttpServletResponse response) {
		Produto resourceById = produtoService.findOne(id);
		eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
		return resourceById;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Produto resource, HttpServletResponse response) throws Exception {
		Long newId = produtoService.create(resource).getId();
		eventPublisher.publishEvent(new ResourceCreated(this, response, newId));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update(@PathVariable Long id, @Valid @RequestBody Produto resource, HttpServletResponse response) {
		try {
			Produto produtoSalvo = produtoService.update(id, resource);
			return ResponseEntity.ok(produtoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
