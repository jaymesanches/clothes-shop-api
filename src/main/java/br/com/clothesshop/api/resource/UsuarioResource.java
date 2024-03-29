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

import br.com.clothesshop.api.model.Usuario;
import br.com.clothesshop.api.service.UsuarioService;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioResource {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> findAll(HttpServletResponse response) {
		List<Usuario> usuarios = usuarioService.findAll();
		eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
		return usuarios;
	}

	@GetMapping(value = "/{id}")
	public Usuario findById(@PathVariable("id") Long id, HttpServletResponse response) {
		Usuario resourceById = usuarioService.findOne(id);
		eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
		return resourceById;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Usuario resource, HttpServletResponse response) throws Exception {
		Long newId = usuarioService.create(resource).getId();
		eventPublisher.publishEvent(new ResourceCreated(this, response, newId));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @Valid @RequestBody Usuario resource, HttpServletResponse response) {
		try {
			Usuario usuarioSalvo = usuarioService.update(id, resource);
			return ResponseEntity.ok(usuarioSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
