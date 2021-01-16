package com.ccrecruiter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccrecruiter.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	public List<Postagem> findByContatoContainingIgnoreCase(String contato);
	
	public List<Postagem> findByModalidadeContainingIgnoreCase(String modalidade);

}
