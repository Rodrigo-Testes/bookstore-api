package com.rodrigo.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	// metodo do Servico de busca a categoria pelo id
	
	public Categoria findById(Integer id) {
		Optional<Categoria>obj = repository.findById(id);
		//retorna nada essa mensagem caso nao tem nada nesse ID
		return obj.orElseThrow(() 
				 -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: " 
				 + Categoria.class.getName())); 
	}
	
	//=======================================================================================================
	//metodo de Servico para trazer todas os dados da categorias
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	//=======================================================================================================
	//metodo de Servico para criar (CREATE) uma categoria
	
	public Categoria create(Categoria obj) {
		obj.setId(null); //id ja vai recer null para nao atualizar o id que ja existe []
		return repository.save(obj);
	}
	
	//=======================================================================================================
	//metodo de Servico para fazer a Atualizacao (Update) uma categoria
	
	public Categoria update(Integer id, CategoriaDTO objDto) {
		Categoria obj = findById(id); //encontrando o id que quer atualizar
		obj.setNome(objDto.getNome()); // atualizando o nome da categoria
		obj.setDescricao(objDto.getDescricao()); //atualizando o nome da descricao da categoria
		return repository.save(obj); //salvando as informacoes atualizadas no banco de dados gracas ao Repository
	}

	//=======================================================================================================
	//metodo de Servico para deletar (DELETE) uma categoria
	
	public void delete(Integer id) {
		findById(id); //verificando se existe esse id para deletar
		repository.deleteById(id);
	}
	
}
