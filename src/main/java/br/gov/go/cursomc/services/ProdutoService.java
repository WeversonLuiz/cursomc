package br.gov.go.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.gov.go.cursomc.domain.Categoria;
import br.gov.go.cursomc.domain.Produto;
import br.gov.go.cursomc.repositories.CategoriaRepository;
import br.gov.go.cursomc.repositories.ProdutoRepository;
import br.gov.go.cursomc.services.Exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id){
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Produto: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.findDistictByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
}
