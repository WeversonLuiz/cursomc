package br.gov.go.cursomc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.gov.go.cursomc.domain.Categoria;
import br.gov.go.cursomc.domain.Cidade;
import br.gov.go.cursomc.domain.Cliente;
import br.gov.go.cursomc.domain.Endereco;
import br.gov.go.cursomc.domain.Estado;
import br.gov.go.cursomc.domain.ItemPedido;
import br.gov.go.cursomc.domain.PagaementoComBoleto;
import br.gov.go.cursomc.domain.Pagamento;
import br.gov.go.cursomc.domain.PagamentoComCartao;
import br.gov.go.cursomc.domain.Pedido;
import br.gov.go.cursomc.domain.Produto;
import br.gov.go.cursomc.domain.enums.EstadoPagamento;
import br.gov.go.cursomc.domain.enums.TipoCliente;
import br.gov.go.cursomc.repositories.CategoriaRepository;
import br.gov.go.cursomc.repositories.CidadeRepository;
import br.gov.go.cursomc.repositories.ClienteRepository;
import br.gov.go.cursomc.repositories.EnderecoRepository;
import br.gov.go.cursomc.repositories.EstadoRepository;
import br.gov.go.cursomc.repositories.ItemPedidoRepository;
import br.gov.go.cursomc.repositories.PagamentoRepository;
import br.gov.go.cursomc.repositories.PedidoRepository;
import br.gov.go.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			CidadeRepository cidadeRepository, EstadoRepository estadoRepository, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository,
			PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository, ItemPedidoRepository itemPedidoRepository){
		return args -> {
			initCategorias(categoriaRepository, produtoRepository, cidadeRepository, estadoRepository, clienteRepository, enderecoRepository,
						pedidoRepository, pagamentoRepository, itemPedidoRepository);
		};
	}
	
	private void initCategorias(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			CidadeRepository cidadeRepository, EstadoRepository estadoRepository, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository,
			PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository, ItemPedidoRepository itemPedidoRepository) throws ParseException{
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Eletrônicos");
		Categoria cat4 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Movelaria");
		Categoria cat7 = new Categoria(null, "Padaria");
		Categoria cat8 = new Categoria(null, "Salchicharia");
		Categoria cat9 = new Categoria(null, "Peixaria");
		Categoria cat10 = new Categoria(null, "Perfumaria");
		Categoria cat11 = new Categoria(null, "Cosméticos");
		Categoria cat12 = new Categoria(null, "Papelaria");
		
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
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
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
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagaementoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00: 00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
