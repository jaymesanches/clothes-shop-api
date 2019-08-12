package br.com.clothesshop.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clothesshop.api.model.Grupo;
import br.com.clothesshop.api.repository.grupo.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	public Grupo findOne(Long id) {
		return grupoRepository.getOne(id);
	}

	public Grupo create(Grupo resource) {
		return grupoRepository.save(resource);
	}

	public List<Grupo> findAll() {
		return grupoRepository.findAll();
	}

	public Grupo update(Long id, Grupo grupo) {
		Grupo grupoSalvo = grupoRepository.getOne(id);
		BeanUtils.copyProperties(grupo, grupoSalvo, "id");
		return grupoRepository.save(grupoSalvo);
	}
}
