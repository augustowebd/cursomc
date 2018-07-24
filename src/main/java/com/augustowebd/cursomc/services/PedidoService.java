package com.augustowebd.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augustowebd.cursomc.domain.Pedido;
import com.augustowebd.cursomc.repositories.PedidoRepository;
import com.augustowebd.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido findById(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(
			() -> new ObjectNotFoundException(
	                	"Objeto não encontrado! Id: " + id +
	                	", Tipo: " + Pedido.class.getName()
					)
		);
	}
}
