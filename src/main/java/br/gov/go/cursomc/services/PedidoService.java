package br.gov.go.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.cursomc.domain.Cliente;
import br.gov.go.cursomc.domain.ItemPedido;
import br.gov.go.cursomc.domain.PagamentoComBoleto;
import br.gov.go.cursomc.domain.Pedido;
import br.gov.go.cursomc.domain.enums.EstadoPagamento;
import br.gov.go.cursomc.repositories.ClienteRepository;
import br.gov.go.cursomc.repositories.ItemPedidoRepository;
import br.gov.go.cursomc.repositories.PagamentoRepository;
import br.gov.go.cursomc.repositories.PedidoRepository;
import br.gov.go.cursomc.security.UserSS;
import br.gov.go.cursomc.services.Exceptions.AuthorizationException;
import br.gov.go.cursomc.services.Exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository ItemPedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public Pedido find(Integer id){
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj){
		obj.setId(null);
		obj.setInstante(new Date());
		Optional<Cliente> cliente = clienteRepository.findById(obj.getCliente().getId());
		obj.setCliente(cliente.orElse(null));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido item : obj.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(produtoService.find(item.getProduto().getId()));
			item.setPreco(produtoService.find(item.getProduto().getId()).getPreco());
			item.setPedido(obj);
		}
		ItemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
		
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		UserSS user = UserService.UsuarioAutenticado();
		if (user == null) {
			throw new AuthorizationException("Acesso negado.");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.find(user.getId());
		return pedidoRepository.findByCliente(cliente, pageRequest);
	}
	
}
