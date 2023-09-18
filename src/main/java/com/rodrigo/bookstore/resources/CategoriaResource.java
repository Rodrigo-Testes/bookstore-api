package com.rodrigo.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.bookstore.domain.Categoria;
import com.rodrigo.bookstore.dtos.CategoriaDTO;
import com.rodrigo.bookstore.services.CategoriaService;

//as requisicoes do front-end chega na camada de resource [Rest]

@RestController //informando ao spring que aqui e a classe vai receber requisicoes de outras fontes
@RequestMapping(value = "/categorias" )
public class CategoriaResource {
	
	@Autowired 
	private CategoriaService service; //chamando o servico na classe CategoriaService
	
	//============================================================================================================
	//pegando o end point pelo id
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id){
		Categoria obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//====================================================================
	//end point para as listagem das categorias disponveis
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> list = service.findAll();
		//transformando a lista de Categoria para DTO
		//List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		List<CategoriaDTO> listDTO = list.stream().map(obj -> CategoriaDTO.builder().id(obj.getId()).build()).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	//====================================================================
	//end point para Criar 
	
	@PostMapping
	public ResponseEntity<Categoria> create(@RequestBody Categoria obj){
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	//====================================================================
	//end point de update
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @RequestBody CategoriaDTO objDto){
		Categoria newObj = service.update(id,objDto);
		//return ResponseEntity.ok().body(new CategoriaDTO(newObj));
		return ResponseEntity.ok().body(CategoriaDTO.builder().id(newObj.getId()).build());
	}
	
	//====================================================================
	//end point de DELETE
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//====================================================================
	//end point de DELETE
	
}
