package br.com.jobbackenddeveloper.jobbackenddeveloper.security.input;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

public class ClienteInput {
	
	private String usuario;
	private String senha;
	private String nome;
	private String job;
	private String hobby;

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

	@NotNull(message = "Job não pode ser vazio.")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@NotNull(message = "Hobby não pode ser vazio.")
	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
}
