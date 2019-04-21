package br.com.jobbackenddeveloper.jobbackenddeveloper.domain;

import br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded.PerfilEnum;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements UserDetails, Serializable {

	@GenericGenerator(name = "clienteSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters ={@org.hibernate.annotations.Parameter(name = "sequence_name", value = "CLIENTE_SEQUENCE"),
					@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
					@org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="clienteSequenceGenerator")
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name="USUARIO", nullable = false, unique=true)
	private String usuario;

	@Column(name="SENHA", nullable = false)
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(name = "PERFIL", nullable = false)
	private PerfilEnum perfil;

	@Column(name = "HOBBY")
	private String hobby;

	@Column(name = "JOB")
	private String job;

	public Cliente () {
	}

	public Cliente (String nome, String usuario, String senha, PerfilEnum perfil, String hobby, String job) {
		this.setNome(nome);
		this.setUsuario(usuario);
		this.setSenha(senha);
		this.setPerfil(perfil);
		this.setHobby(hobby);
		this.setJob(job);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> authoritySet = new HashSet<>();

		authoritySet.add(new SimpleGrantedAuthority(perfil.name()));

		return authoritySet;

	}
	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
