package br.com.jobbackenddeveloper.jobbackenddeveloper.security.input;

import javax.validation.constraints.NotNull;

public class JwtAuthenticationInput {

	private String usuario;
	private String senha;

	public JwtAuthenticationInput() {
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

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [usuario=" + usuario + ", senha=" + senha + "]";
	}

}
