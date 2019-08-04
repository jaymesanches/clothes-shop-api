package br.com.clothesshop.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clothesshop.api.model.Usuario;
import br.com.clothesshop.api.repository.UsuarioRepository;
import br.com.clothesshop.api.service.exception.EmailJaCadastradoException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario findOne(Long id) {
		return usuarioRepository.getOne(id);
	}

	public Usuario create(Usuario usuario) throws EmailJaCadastradoException {
		Optional<Usuario> optional = usuarioRepository.findByEmail(usuario.getEmail());
		if (optional.isPresent()) {
			throw new EmailJaCadastradoException();
		}
		return usuarioRepository.save(usuario);
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario update(Long id, Usuario usuario) {
		Usuario usuarioSalvo = usuarioRepository.getOne(id);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		return usuarioRepository.save(usuarioSalvo);
	}

	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

}
