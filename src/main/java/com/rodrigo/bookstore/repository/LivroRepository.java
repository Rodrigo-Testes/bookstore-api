package com.rodrigo.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rodrigo.bookstore.domain.Livro;

@Repository

public interface LivroRepository extends JpaRepository<Livro, Integer>{

	//metodo para listar todos os livros pela determinada categoria passando pelo argumento
	@Query("SELECT obj FROM tb_livro obj WHERE obj.categoria.id = :id_cat ORDER BY titulo")
	//@Query("SELECT livro FROM Livro livro WHERE livro.categoria = :categoria")
	List<Livro> findAllByCategoria(@Param(value = "id_cat")Integer id_cat);
	

}
