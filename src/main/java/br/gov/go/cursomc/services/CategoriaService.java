package br.gov.go.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.cursomc.domain.Categoria;
import br.gov.go.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id){
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElse(null);
	}
	
}
