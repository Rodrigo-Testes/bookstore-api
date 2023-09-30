package com.rodrigo.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rodrigo.bookstore.domain.Categoria;
import com.rodrigo.bookstore.dtos.CategoriaDTO;
import com.rodrigo.bookstore.repository.CategoriaRepository;
import com.rodrigo.bookstore.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	//=======================================================================================================
	// camada do servico de busca a categoria pelo [GET] id
	
	public Categoria findById(Integer id) {
		Optional<Categoria>obj = repository.findById(id);
		//retorna nada essa mensagem caso nao tem nada nesse ID
		return obj.orElseThrow(() 
				 -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: " 
				 + Categoria.class.getName())); 
	}
	
	//=======================================================================================================
	//camada do servicopara trazer todas os dados da categorias
	
	public List<Categoria> findAll(){
		return repository.findAll();
	} 
	
	//=======================================================================================================
	//camada do servico para criar (CREATE) uma categoria
	
	public Categoria create(Categoria obj) {
		obj.setId(null); //id ja vai recer null para nao atualizar o id que ja existe []
		return repository.save(obj);
	}
	
	//=======================================================================================================
	//camada do servico para fazer a Atualizacao (Update) uma categoria
	
	public Categoria update(Integer id, CategoriaDTO objDto) {
		//faz a mesma coisa da linha 54 e 55, ele da sets nos campos
//		obj = Categoria.builder()
//				.nome(objDto.getNome())
//				.descricao(objDto.getDescricao())
//				.build();
		
		Categoria obj = findById(id); //encontrando o id que quer atualizar
		obj.setNome(objDto.getNome()); // atualizando o nome da categoria
		obj.setDescricao(objDto.getDescricao()); //atualizando o nome da descricao da categoria
		return repository.save(obj); //salvando as informacoes atualizadas no banco de dados gracas ao Repository
	}

	//=======================================================================================================
	//camada do servico para deletar (DELETE) uma categoria
	
	public void delete(Integer id) {
		findById(id); //verificando se existe esse id para deletar
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.rodrigo.bookstore.services.exceptions.DataIntegrityViolationException(""
					+ "Categoria nao pode ser deletado! Possui livros associados");
		}
	}
	
}
