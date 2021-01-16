package com.ccrecruiter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccrecruiter.model.Postagem;
import com.ccrecruiter.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/contato/{contato}")
	public ResponseEntity<List<Postagem>> getByContato(@PathVariable String contato){
		return ResponseEntity.ok(repository.findByContatoContainingIgnoreCase(contato));
	}
	
	@GetMapping("/modalidade/{modalidade}")
	public ResponseEntity<List<Postagem>> getByModalidade(@PathVariable String modalidade){
		return ResponseEntity.ok(repository.findByModalidadeContainingIgnoreCase(modalidade));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Postagem> putPostagem(@PathVariable Long id, @RequestBody Postagem postagem){
		postagem.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
	
