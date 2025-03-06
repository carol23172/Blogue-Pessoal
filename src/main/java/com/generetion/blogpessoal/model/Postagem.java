package com.generetion.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_postagens")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
	@NotBlank(message = " O atributo titulo é obrigatório")
    @Size(min = 5, max = 100, message = "O título tem que ser maior que 5 e menor que 100")
   private String titulo;
   
	@NotBlank(message = " O atributo texto é obrigatório")
    @Size(min = 5, max = 1000, message = "O atributo texto tem que ser maior que 5 e menor que 1000")
   private String texto;
   
	   @UpdateTimestamp
	    private LocalDateTime data;

	   @ManyToOne //Classe postagem e muitos : classe tema e um
	   @JsonIgnoreProperties("postagem")
	   private Tema tema; // aq adicionando o objeto tema(id,descricao)
	   
	   
	   public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Long getId() {
		 return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
  
   
   
}
