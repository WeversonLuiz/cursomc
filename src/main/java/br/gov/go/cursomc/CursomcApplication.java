package br.gov.go.cursomc;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.gov.go.cursomc.domain.Categoria;
import br.gov.go.cursomc.domain.Cidade;
import br.gov.go.cursomc.domain.Estado;
import br.gov.go.cursomc.domain.Produto;
import br.gov.go.cursomc.repositories.CategoriaRepository;
import br.gov.go.cursomc.repositories.CidadeRepository;
import br.gov.go.cursomc.repositories.EstadoRepository;
import br.gov.go.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			CidadeRepository cidadeRepository, EstadoRepository estadoRepository){
		return args -> {
			initCategorias(categoriaRepository, produtoRepository, cidadeRepository, estadoRepository);
		};
	}
	
	private void initCategorias(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			CidadeRepository cidadeRepository, EstadoRepository estadoRepository){
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Eletrônicos");
		Categoria cat4 = new Categoria(null, "Cama, Mesa e Banho");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Teclado", 120.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p1, p2));
		cat3.getProdutos().addAll(Arrays.asList(p3, p4));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat3));
		p4.getCategorias().addAll(Arrays.asList(cat3));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlãndia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	}
}
