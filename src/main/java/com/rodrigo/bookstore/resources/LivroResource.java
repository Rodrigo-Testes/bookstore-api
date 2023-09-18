package com.rodrigo.bookstore.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.bookstore.domain.Livro;
import com.rodrigo.bookstore.dtos.LivroDTO;
import com.rodrigo.bookstore.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
	
	@Autowired
	private LivroService livroService;
	
	//metodo FindByID de Livros
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id){
		Livro livro = livroService.findById(id);
		return ResponseEntity.ok().body(livro);
	}
	
	//====================================================================
	//end point de como vai ficar os livros de uma determinada categoria
	// localhost:8080/livros?categoria=1
	
	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat){
		List<Livro> listLivro = livroService.findAll(id_cat);
		//convertendo a lista de livro em lista de livro DTO
		//List<LivroDTO> listLivroDTO = listLivro.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList()); 
		List<LivroDTO> listLivroDTO = listLivro.stream().map(obj -> LivroDTO.builder().titulo(obj.getTitulo()).id(obj.getId()).build()).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listLivroDTO);
	}
	
	//==================================================================
	//end point de UPDATE
	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro livroDTO) {
		Livro livro = livroService.update(id,livroDTO);
		return ResponseEntity.ok().body(livro);
	}
	
	//==================================================================
	//end point de PATCH
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro livroDTO) {
		Livro livro = livroService.update(id,livroDTO);
		return ResponseEntity.ok().body(livro);
	}
}
