package com.augustowebd.cursomc.repositories;

import org.springframework.stereotype.Repository;

import com.augustowebd.cursomc.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
}
