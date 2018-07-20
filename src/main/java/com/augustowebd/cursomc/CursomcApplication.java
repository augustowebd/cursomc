package com.augustowebd.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.augustowebd.cursomc.domain.Categoria;
import com.augustowebd.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository repo; 

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cInfo = new Categoria(null, "Informática");
		Categoria cEscr = new Categoria(null, "Escritório");
		
		repo.saveAll(Arrays.asList(cInfo, cEscr));
	}
}
