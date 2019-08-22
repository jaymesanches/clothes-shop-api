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

import br.com.clothesshop.api.model.Cliente;
import br.com.clothesshop.api.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteResource {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> findAll(HttpServletResponse response) {
		List<Cliente> clientes = clienteService.findAll();
		eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
		return clientes;
	}

	@GetMapping("/{id}")
	public Cliente findById(@PathVariable("id") Long id, HttpServletResponse response) {
		Cliente resourceById = clienteService.findOne(id);
		eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
		return resourceById;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Cliente resource, HttpServletResponse response) {
		Long newId = clienteService.create(resource).getId();
		eventPublisher.publishEvent(new ResourceCreated(this, response, newId));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody Cliente cliente,
			HttpServletResponse response) {
		try {
			Cliente grupoSalvo = clienteService.update(id, cliente);
			return ResponseEntity.ok(grupoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
