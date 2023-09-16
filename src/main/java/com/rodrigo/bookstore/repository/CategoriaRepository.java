package com.rodrigo.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.bookstore.domain.Categoria;

//os Respository é a classe onde faz tudo do banco de Dados
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
