package com.rodrigo.bookstore.domain;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity(name = "tb_livro") //nome da tabela banco de dados com [javapersistence]
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Campo TITULO e requerido")
	@Length(min = 3, max = 50, message = "O campo TITU")
	private String titulo;
	
	@NotEmpty(message = "Campo NOME DO AUTOR e requerido")
	@Length(min = 3, max = 50, message = "O campo NOME DO AUTO")
	private String nome_autor;
	
	@NotEmpty(message = "Campo TEXTO e requerido")
	@Length(min = 3, max = 100,message = "Campo TEXTO e requerido")
	//@Column(name = "texto", length = 20000)
	private String texto;

	// associacao
	@JsonIgnore //e
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	// ==============================================================================
	// constructs

	public Livro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Livro(Integer id, String titulo, String nome_autor, String texto, Categoria categoria) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.nome_autor = nome_autor;
		this.texto = texto;
		this.categoria = categoria;
	}

	// ==============================================================================
	// getters e setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNome_autor() {
		return nome_autor;
	}

	public void setNome_autor(String nome_autor) {
		this.nome_autor = nome_autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	// ==============================================================================
	// hashcode e equals
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}
}
