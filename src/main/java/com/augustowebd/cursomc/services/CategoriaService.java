package com.augustowebd.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augustowebd.cursomc.domain.Categoria;
import com.augustowebd.cursomc.repositories.CategoriaRepository;
import com.augustowebd.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria findById(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(
			() -> new ObjectNotFoundException(
	                	"Objeto não encontrado! Id: " + id +
	                	", Tipo: " + Categoria.class.getName()
					)
		);
	}
}
