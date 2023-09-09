package com.rodrigo.bookstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigo.bookstore.domain.Categoria;
import com.rodrigo.bookstore.domain.Livro;
import com.rodrigo.bookstore.repository.CategoriaRepository;
import com.rodrigo.bookstore.repository.LivroRepository;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {
	
	//instanciando as Interfaces [Repository] para persistir no banco
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
//====================================================================================
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	//==============================================================
	//comando para adicionar as informacoes

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica", "Livro de TI");
		
		Livro l1 = new Livro(null, "Clean Code", "Rodrigo Oliveira", "Lorem ipsum", cat1); //aqui o livro ja sabem quem e a vategoria
		
		cat1.getLivros().addAll(Arrays.asList(l1)); //aqui estamos fazendo a categoria conhecer o livros pq ele ainda nao conehce
		
		//salvando as informacoes no banco com
		
		this.categoriaRepository.saveAll(Arrays.asList(cat1)); //salvando na categorias
		this.livroRepository.saveAll(Arrays.asList(l1)); // salvando no livro
	}

}
