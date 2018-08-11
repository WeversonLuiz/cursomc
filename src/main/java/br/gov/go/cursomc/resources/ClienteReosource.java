package br.gov.go.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.cursomc.domain.Cliente;
import br.gov.go.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteReosource {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Cliente cliente = this.clienteService.buscar(id);
		return ResponseEntity.ok().body(cliente);
	}

}
