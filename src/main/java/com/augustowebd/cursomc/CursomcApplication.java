package com.augustowebd.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.augustowebd.cursomc.domain.Categoria;
import com.augustowebd.cursomc.domain.Cidade;
import com.augustowebd.cursomc.domain.Cliente;
import com.augustowebd.cursomc.domain.Endereco;
import com.augustowebd.cursomc.domain.Estado;
import com.augustowebd.cursomc.domain.ItemPedido;
import com.augustowebd.cursomc.domain.Pagamento;
import com.augustowebd.cursomc.domain.PagamentoComBoleto;
import com.augustowebd.cursomc.domain.Pedido;
import com.augustowebd.cursomc.domain.Produto;
import com.augustowebd.cursomc.domain.enuns.EstadoPagamento;
import com.augustowebd.cursomc.domain.enuns.TipoCliente;
import com.augustowebd.cursomc.domain.PagamentoComCartao;
import com.augustowebd.cursomc.repositories.CategoriaRepository;
import com.augustowebd.cursomc.repositories.CidadeRepository;
import com.augustowebd.cursomc.repositories.ClienteRepository;
import com.augustowebd.cursomc.repositories.EnderecoRepository;
import com.augustowebd.cursomc.repositories.EstadoRepository;
import com.augustowebd.cursomc.repositories.ItemPedidoRepository;
import com.augustowebd.cursomc.repositories.PagamentoRepository;
import com.augustowebd.cursomc.repositories.PedidoRepository;
import com.augustowebd.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;
	
	@Autowired
	private ProdutoRepository proRepo;
	
	@Autowired
	private EstadoRepository estRepo;
	
	@Autowired
	private CidadeRepository cidRepo;
	
	@Autowired
	private ClienteRepository cliRepo;
	
	@Autowired
	private EnderecoRepository endRepo;
	
	@Autowired
	private PedidoRepository pedRepo;
	
	@Autowired
	private PagamentoRepository pagRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Produto pComp 		= new Produto(null, "Computador", 2000.00d);
		Produto pImpressora = new Produto(null, "Impressora", 800.00d);
		Produto pMouse 		= new Produto(null, "Mouse", 80.00d);
		
		Categoria cInfo = new Categoria(null, "Informática");
		cInfo.getProdutos().addAll(Arrays.asList(pComp, pImpressora, pMouse));
		
		Categoria cEscr = new Categoria(null, "Escritório");
		cEscr.getProdutos().addAll(Arrays.asList(pImpressora));
		
		pComp.getCategorias().addAll(Arrays.asList(cInfo));	
		pImpressora.getCategorias().addAll(Arrays.asList(cInfo, cEscr));
		pMouse.getCategorias().addAll(Arrays.asList(cInfo));
		
		catRepo.saveAll(Arrays.asList(cInfo, cEscr));
		proRepo.saveAll(Arrays.asList(pComp, pImpressora, pMouse));
		/* 				-*- end of produtos -*-				 */
		
		
		Estado eMinasGer = new Estado(null, "Minas Gerais");
		Estado eSaoPaulo = new Estado(null, "São Paulo"	  );
		
		Cidade cidUberland = new Cidade(null, "Uberlândia", eMinasGer);	
		Cidade cidCampinas = new Cidade(null, "Campinas",  eSaoPaulo );
		Cidade cidSaoPaulo = new Cidade(null, "São Paulo", eSaoPaulo );		

		eMinasGer.getCidades().addAll(Arrays.asList(cidUberland));				
		eSaoPaulo.getCidades().addAll(Arrays.asList(cidCampinas, cidSaoPaulo));
		
		estRepo.saveAll(Arrays.asList(eMinasGer, eSaoPaulo));
		cidRepo.saveAll(Arrays.asList(cidUberland, cidCampinas, cidSaoPaulo));
		/* -*- */

		Cliente clMariaSilva = new Cliente(null, "Maria Silva", "maria.silva@gmail.com", "12345678909", TipoCliente.PESSOA_FISICA);
		clMariaSilva.getTelefones().addAll(Arrays.asList("3333-4444", "3333-5555"));
		
		Endereco eRuaFlores = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "35220831", clMariaSilva, cidUberland);
		Endereco eAvenMatos = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "3877012", clMariaSilva, cidSaoPaulo);
		
		clMariaSilva.getEnderecos().addAll(Arrays.asList(eRuaFlores, eAvenMatos));
		
		cliRepo.saveAll(Arrays.asList(clMariaSilva));
		endRepo.saveAll(Arrays.asList(eRuaFlores, eAvenMatos));
		
		/* *-* */
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), clMariaSilva, eRuaFlores);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), clMariaSilva, eAvenMatos);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:01"), null);
		ped2.setPagamento(pagto2);
		
		clMariaSilva.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedRepo.saveAll(Arrays.asList(ped1, ped2));
		pagRepo.saveAll(Arrays.asList(pagto1, pagto2));
		
		/* -*- */
		ItemPedido ip1 = new ItemPedido(ped1, pComp, 0.0D, 1, 2000.0D);
		ItemPedido ip2 = new ItemPedido(ped1, pMouse, 0.0D, 2, 80.0D);
		ItemPedido ip3 = new ItemPedido(ped2, pImpressora, 100.0D, 1, 800.0D);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		pComp.getItens().addAll(Arrays.asList(ip1));
		pMouse.getItens().addAll(Arrays.asList(ip2));
		pImpressora.getItens().addAll(Arrays.asList(ip3));
		
		itemPedRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
