package com.generetion.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generetion.blogpessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
    // faz controle de versao para essa classe
	
	private String userName;//email
	private String password;//senha
	private List<GrantedAuthority> authorities;
    //autorizacoes que o usuario tem
	
	//metodo construtor
	public UserDetailsImpl(Usuario user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}

	public UserDetailsImpl() {	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	//pega/retorna a senha
	@Override
	public String getPassword() {

		return password;
	}

	//pega/retorna 
	@Override
	public String getUsername() {

		return userName;
	}

	
	//ajuda a verificar se o acesso ja expirou
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//auxiliar a validacao se o usuario esta bloqueado
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//auxiliar valida se a credencial expira
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//valida se o user esta ativo
	@Override
	public boolean isEnabled() {
		return true;
	}

}
