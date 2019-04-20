package br.com.jobbackenddeveloper.jobbackenddeveloper.security.input;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

public class ClienteInput {
	
	private String usuario;
	private String senha;
	private String nome;

	public ClienteInput() {
	}
	
	@NotNull(message = "Usuario não pode ser vazio.")
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@NotNull(message = "Senha não pode ser vazia.")
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@NotNull(message = "Nome não pode ser vazio.")
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
