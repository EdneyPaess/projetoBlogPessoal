package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

import ch.qos.logback.core.status.Status;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	
	//getAll() 🡪 Retorna todos os Objetos da Classe Postagem persistidos no Banco de dados.
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	//getById(Long id) 🡪 Retorna um Objeto específico da Classe Postagem persistido no Banco de dados. A Postagem é identificada pelo Atributo id.
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		return postagemRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//getByTitulo(String titulo) 🡪 Retorna todos os Objetos da Classe Postagem persistidos no Banco de dados, cujo Atributo titulo contenha a String enviada no parâmetro titulo do Método.
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		
	}
	
	//Método post(Postagem postagem) 🡪 Persiste (salva) um novo Objeto da Classe Postagem no Banco de dados
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
		if (temaRepository.existsById(postagem.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(postagemRepository.save(postagem));
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	//Método put(Postagem postagem) 🡪 Atualiza um Objeto da Classe Postagem persistido no Banco de dados.
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		if (postagemRepository.existsById(postagem.getId())){
			
			if (temaRepository.existsById(postagem.getTema().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(postagemRepository.save(postagem));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		postagemRepository.deleteById(id);				
	}
	
}
