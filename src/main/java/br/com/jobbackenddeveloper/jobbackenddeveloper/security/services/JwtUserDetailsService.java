package br.com.jobbackenddeveloper.jobbackenddeveloper.security.services;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.repository.ClienteRepository;
import br.com.jobbackenddeveloper.jobbackenddeveloper.security.JwtClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service(value = "JwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Optional<Cliente> client = clienteRepository.findByUsuario(usuario);

		if (client.isPresent()) {
			return JwtClientFactory.create(client.get());
		}

		throw new UsernameNotFoundException("Usuario não encontrado.");
	}





}
