package com.generetion.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generetion.blogpessoal.model.Usuario;
import com.generetion.blogpessoal.repository.UsuarioRepository;
import com.generetion.blogpessoal.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
		
		usuarioService.cadastrarUsuario(new Usuario(null, "Root","root@root.com", "12345678", "" ));
      }
	
	@Test
	@DisplayName("Cadastrar um ususario ... #")
	public void deveCriarUmUsuario() {
	HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>( new Usuario(null,
      "Carolina", "carolina@gmail.com", "123456789", "_"));
	
	
	ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange(
			"/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);
	
	assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	}
	
	
	@Test
	@DisplayName("Não deve permitir duplicação do Usuário")
	public void naoDeveDuplicarUsuario() {

	    usuarioService.cadastrarUsuario(new Usuario(null,
	        "Carolina", "carolina@gmail.com", "134652789", "-"));

	    HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(null,
	        "Carolina", "carolina@gmail.com", "134652789", "-"));

	    ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange(
	    		"/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);

	    assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Atualizar um Usuário")
	public void deveAtualizarUmUsuario() {

	    Optional<Usuario> usuarioCadastrado =  usuarioService.cadastrarUsuario(new Usuario(null,
	    		"Juliana Andrews", "Juliana_andrews@gmail.com", "juliana123", "_"));
	    
	    Usuario usuarioUpdate = new Usuario (usuarioCadastrado.get().getId(),
	    "Juliana Andrews Ramos", "Juliana_ramos@gmail.com", "juliana123", "_");
	    
	    HttpEntity<Usuario> corpoRequisicao =  new HttpEntity<Usuario>(usuarioUpdate);
	    
	    ResponseEntity<Usuario> corpoResposta = testRestTemplate
	    		.withBasicAuth("root@root.com","12345678") 
	    	    .exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuario.class);
	    		
	    		  assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	    		 }
	
	@Test
	@DisplayName("Listar Todos os Usuário")
	public void deveMostrarTodosUsuario() {

	    usuarioService.cadastrarUsuario(new Usuario(null,
	        "Sabrina Sanches", "sabrina_sanches@gmail.com", "sabrina134", "-"));
	    
	    usuarioService.cadastrarUsuario(new Usuario(null,
		        "Ricardo Marques", "ricardo_marques@gmail.com", "ricardo134", "-"));

	    ResponseEntity<String> resposta = testRestTemplate
	    		.withBasicAuth("root@root.com","12345678") 
	    	    .exchange("/usuarios/all", HttpMethod.GET, null, String.class);

	    assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	
}
	
	
	
	
	
	
	
	

