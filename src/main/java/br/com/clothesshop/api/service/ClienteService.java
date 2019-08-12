package br.com.clothesshop.api.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clothesshop.api.model.Cliente;
import br.com.clothesshop.api.repository.cliente.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findOne(Long id) {
		return clienteRepository.getOne(id);
	}

	public Cliente create(@Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente update(Long id, @Valid Cliente cliente) {
		Cliente clienteSalvo = this.findOne(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		return clienteRepository.save(clienteSalvo);
	}

}
