package com.rodrigo.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.bookstore.domain.Categoria;
import com.rodrigo.bookstore.domain.Livro;
import com.rodrigo.bookstore.dtos.LivroDTO;
import com.rodrigo.bookstore.repository.LivroRepository;
import com.rodrigo.bookstore.services.exceptions.ObjectNotFoundException;

@Service
public class LivroService {

	//os repository e a camada que faza conexao com a base de dados,persistencia de informacoes
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	//=======================================================================================================
	// camada do servico de busca a categoria pelo [GET] id
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = livroRepository.findById(id);
		//retorna nada essa mensagem caso nao tem nada nesse ID
		return obj.orElseThrow(() 
				 -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: " 
				 + Categoria.class.getName())); 
	}
	
	//=======================================================================================================
	//camada do servicopara trazer todas os dados da categorias
	
	public List<Livro> findAll(Integer id_cat){
		categoriaService.findById(id_cat); //validacao para ve se a categoria existe na base de dados 
		return livroRepository.findAllByCategoria(id_cat);
	}
	//=======================================================================================================
	//camada do servico para fazer a Atualizacao (Update) uma categoria

	public Livro update(Integer id, Livro AtuaLivro) {
		Livro livro = findById(id); //validando o id para ve se existe
		updateData(livro, AtuaLivro);
		return livroRepository.save(livro);
	}

	//metodo responsavel por atualizar os dados
	private void updateData(Livro livro, Livro atuaLivro) {
		livro.setTitulo(atuaLivro.getTitulo());
		livro.setNome_autor(atuaLivro.getNome_autor());
		livro.setTexto(atuaLivro.getTexto());
	}
}
