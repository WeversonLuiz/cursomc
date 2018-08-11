package br.gov.go.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.cursomc.domain.Cliente;
import br.gov.go.cursomc.repositories.ClienteRepository;
import br.gov.go.cursomc.services.Exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:"+ id + ", Cliente: " + Cliente.class.getName())); 
	}

}
