package com.augustowebd.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augustowebd.cursomc.domain.Cliente;
import com.augustowebd.cursomc.repositories.ClienteRepository;
import com.augustowebd.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente findById(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(
			() -> new ObjectNotFoundException(
	                	"Objeto não encontrado! Id: " + id +
	                	", Tipo: " + Cliente.class.getName()
					)
		);
	}
}
