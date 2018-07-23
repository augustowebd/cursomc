package com.augustowebd.cursomc;

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
import com.augustowebd.cursomc.domain.Produto;
import com.augustowebd.cursomc.domain.enuns.TipoCliente;
import com.augustowebd.cursomc.repositories.CategoriaRepository;
import com.augustowebd.cursomc.repositories.CidadeRepository;
import com.augustowebd.cursomc.repositories.ClienteRepository;
import com.augustowebd.cursomc.repositories.EnderecoRepository;
import com.augustowebd.cursomc.repositories.EstadoRepository;
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
	}
}
