package br.com.clothesshop.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clothesshop.api.model.Cliente;
import br.com.clothesshop.api.model.Contato;
import br.com.clothesshop.api.model.Endereco;
import br.com.clothesshop.api.repository.cliente.ClienteRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ClienteRepositoryTest {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ClienteRepository clienteRepository; 
	
	private final String NOME = "Nome do Cliente";
	
	@Test
	public void deve_salvar_cliente_no_repositorio() {
		Cliente clienteSalvo = salvarCliente();
		assertThat(clienteSalvo.getNome()).isEqualTo(NOME);
	}
	
	@Test
	public void deve_atualizar_cliente_no_repositorio() {
		Cliente cliente = salvarCliente();
		cliente.setNome("Novo Nome");
		Cliente clienteSalvo = clienteRepository.save(cliente);
		assertThat(clienteSalvo.getNome()).isEqualTo("Novo Nome");
	}
	
	@Test
	public void deve_salvar_cliente_com_endereco() {
		Cliente cliente = salvarCliente();
		Endereco endereco = new Endereco();
		endereco.setTipo("COMERCIAL");
		endereco.setCep(80540140);
		endereco.setEndereco("Rua 123");
		endereco.setCidade("Cidade");
		endereco.setEstado("PR");
		endereco.setBairro("Bairro");
		endereco.setNumero("123");
		cliente.getEnderecos().add(endereco);
		Cliente clienteSalvo = clienteRepository.save(cliente);
		assertThat(clienteSalvo.getEnderecos()).isNotEmpty();
	}
	
	@Test
	public void deve_salvar_cliente_com_contato() {
		Cliente cliente = salvarCliente();
		Contato contato = new Contato();
		contato.setTipo("TELEFONE");
		contato.setDescricao("5555-5555");
		cliente.getContatos().add(contato);
		Cliente clienteSalvo = clienteRepository.save(cliente);
		assertThat(clienteSalvo.getContatos()).isNotEmpty();
	}
	
	private Cliente salvarCliente() {
		Cliente grupo = novoCliente();
		Cliente usuarioSalvo = clienteRepository.save(grupo);
		entityManager.flush();
		return usuarioSalvo;
	}

	private Cliente novoCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome(NOME);
		return cliente;
	}
}
