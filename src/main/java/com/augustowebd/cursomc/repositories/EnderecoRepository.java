package com.augustowebd.cursomc.repositories;

import org.springframework.stereotype.Repository;

import com.augustowebd.cursomc.domain.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
}
