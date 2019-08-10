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

import br.com.clothesshop.api.model.Grupo;
import br.com.clothesshop.api.service.GrupoService;

@RestController
@RequestMapping("/api/grupos")
public class GrupoResource {

	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private GrupoService grupoService;

	@GetMapping
	public List<Grupo> findAll(HttpServletResponse response) {
		List<Grupo> grupos = grupoService.findAll();
		eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
		return grupos;
	}

	@GetMapping("/{id}")
	public Grupo findById(@PathVariable("id") Long id, HttpServletResponse response) {
		Grupo resourceById = grupoService.findOne(id);
		eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
		return resourceById;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Grupo resource, HttpServletResponse response) {
		Long newId = grupoService.create(resource).getId();
		eventPublisher.publishEvent(new ResourceCreated(this, response, newId));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Grupo> update(@PathVariable Long id, @Valid @RequestBody Grupo resource, HttpServletResponse response) {
		try {
			Grupo grupoSalvo = grupoService.update(id, resource);
			return ResponseEntity.ok(grupoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
