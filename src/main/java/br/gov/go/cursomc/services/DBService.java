package br.gov.go.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.cursomc.domain.Categoria;
import br.gov.go.cursomc.domain.Cidade;
import br.gov.go.cursomc.domain.Cliente;
import br.gov.go.cursomc.domain.Endereco;
import br.gov.go.cursomc.domain.Estado;
import br.gov.go.cursomc.domain.ItemPedido;
import br.gov.go.cursomc.domain.Pagamento;
import br.gov.go.cursomc.domain.PagamentoComBoleto;
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


@Service
public class DBService {


	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	

	public void instantiateTestDataBase() throws ParseException{

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
		Produto p5 = new Produto(null, "Sofá", 2000.00);
		Produto p6 = new Produto(null, "Rossadeira", 800.00);
		Produto p7 = new Produto(null, "Sardinha", 10.00);
		Produto p8 = new Produto(null, "Colônia Jequiti", 120.00);
		Produto p9 = new Produto(null, "Papel A-4", 15.90);
		Produto p10 = new Produto(null, "Shampoo", 19.90);
		Produto p11 = new Produto(null, "Salsicha", 9.90);
		Produto p12 = new Produto(null, "Pão de Queijo", 1.00);

		Produto p13 = new Produto(null, "Monitor", 250.00);
		Produto p14 = new Produto(null, "Calculadora 8DIG", 10.00);
		Produto p15 = new Produto(null, "Poltrona reclinável", 690.00);
		Produto p16 = new Produto(null, "Celular Galazy 20", 1199.90);
		Produto p17 = new Produto(null, "Agenda 2018", 19.90);
		Produto p18 = new Produto(null, "Condicionador", 9.90);
		Produto p19 = new Produto(null, "Bolacha rosquinha de coco", 5.00);
		Produto p20 = new Produto(null, "Caixa arquivo", 3.90);
		Produto p21 = new Produto(null, "Carimbo", 1.00);
		Produto p22 = new Produto(null, "Travesseiro", 31.00);
		Produto p23 = new Produto(null, "Cortina", 99.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4, p13));
		cat2.getProdutos().addAll(Arrays.asList(p20, p21));
		cat3.getProdutos().addAll(Arrays.asList(p14, p16));

		cat4.getProdutos().addAll(Arrays.asList(p22, p23));
		cat5.getProdutos().addAll(Arrays.asList(p6));
		cat6.getProdutos().addAll(Arrays.asList(p5, p15));
		cat7.getProdutos().addAll(Arrays.asList(p12, p19));
		cat8.getProdutos().addAll(Arrays.asList(p11));
		cat9.getProdutos().addAll(Arrays.asList(p7));
		cat10.getProdutos().addAll(Arrays.asList(p8));
		cat11.getProdutos().addAll(Arrays.asList( p10, p18));
		cat12.getProdutos().addAll(Arrays.asList(p9, p17));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat1));

		p5.getCategorias().addAll(Arrays.asList(cat6));
		p6.getCategorias().addAll(Arrays.asList(cat5));
		p7.getCategorias().addAll(Arrays.asList(cat9));
		p8.getCategorias().addAll(Arrays.asList(cat10));
		p9.getCategorias().addAll(Arrays.asList(cat12));
		p10.getCategorias().addAll(Arrays.asList(cat11));
		p11.getCategorias().addAll(Arrays.asList(cat8));
		p12.getCategorias().addAll(Arrays.asList(cat4));
		p13.getCategorias().addAll(Arrays.asList(cat1));
		p14.getCategorias().addAll(Arrays.asList(cat3));
		p15.getCategorias().addAll(Arrays.asList(cat6));
		p16.getCategorias().addAll(Arrays.asList(cat3));
		p17.getCategorias().addAll(Arrays.asList(cat12));
		p18.getCategorias().addAll(Arrays.asList(cat11));
		p19.getCategorias().addAll(Arrays.asList(cat4));
		p20.getCategorias().addAll(Arrays.asList(cat2));
		p21.getCategorias().addAll(Arrays.asList(cat2));
		p22.getCategorias().addAll(Arrays.asList(cat4));
		p23.getCategorias().addAll(Arrays.asList(cat4));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15,
				p16, p17, p18, p19, p20, p21, p22, p23));


		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlãndia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));


		Cliente cli1 = new Cliente(null, "Weverson Luiz", "weversonluiz@hotmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
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

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00: 00"), null);
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
