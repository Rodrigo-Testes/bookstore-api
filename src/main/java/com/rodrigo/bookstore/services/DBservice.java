package com.rodrigo.bookstore.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.bookstore.domain.Categoria;
import com.rodrigo.bookstore.domain.Livro;
import com.rodrigo.bookstore.repository.CategoriaRepository;
import com.rodrigo.bookstore.repository.LivroRepository;

//classe para instanciar os dados mokado

@Service

public class DBservice {
	
	//instanciando as Interfaces [Repository] para persistir no banco
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;

	public void instanciandoBaseDeDados() {
		
		//adicionando informacoes mokado
		Categoria cat1 = new Categoria(null, "Informatica", "Livro de TI");
		Categoria cat2 = new Categoria(null, "Matematica", "Livro de Calculos");
		Categoria cat3 = new Categoria(null, "Ingles", "Livro de Idioma Basico ao Avancado");
		Categoria cat4 = new Categoria(null, "Espanhol", "Livro de Idioma Basico");
		Categoria cat5 = new Categoria(null, "Ciencia", "Livro de Astronomia");
		
		//aqui o livro ja sabem quem e a categoria e adicinando informacoes
		Livro l1 = new Livro(null, "Clean Code", "Rodrigo Oliveira", "Lorem ipsum", cat1); 
		Livro l2 = new Livro(null, "Aritmeticos", "Paulo Gomes", "Segredo da Matematica", cat2);
		Livro l3 = new Livro(null, "Aprendendo o verbo twobee", "Adriana Santos", "ingles", cat3);
		Livro l4 = new Livro(null, "Aprendendo Espanhol", "Kelly Rodrigues", "Espanho Basico", cat4);
		Livro l5 = new Livro(null, "Astronomia e o Gosmos", "Roberto Kaiko", "espaco", cat5);
		
		//aqui estamos fazendo a categoria conhecer o livros pq ele ainda nao conhece
		cat1.getLivros().addAll(Arrays.asList(l1, l2)); 
		cat2.getLivros().addAll(Arrays.asList(l3,l4,l5));
		
		//salvando as informacoes no banco com
		
		this.categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5)); //salvando na categorias
		this.livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5)); // salvando no livro
	}
}
