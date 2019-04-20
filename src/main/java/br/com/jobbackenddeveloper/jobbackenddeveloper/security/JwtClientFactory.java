package br.com.jobbackenddeveloper.jobbackenddeveloper.security;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded.PerfilEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtClientFactory {

	private JwtClientFactory() {
	}

	/**
	 * Converte e gera um JwtClient com base nos dados de um funcionário.
	 * @return JwtClient
	 */
	public static JwtClient create(Cliente client) {
		return new JwtClient(client.getId(), client.getUsuario(), client.getSenha(),
				mapToGrantedAuthorities(client.getPerfil()));
	}
	
	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 *
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}
	
	
}
