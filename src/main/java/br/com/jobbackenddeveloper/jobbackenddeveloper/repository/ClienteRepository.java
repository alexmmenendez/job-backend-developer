package br.com.jobbackenddeveloper.jobbackenddeveloper.repository;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
    Optional<Cliente> findByUsuario(String usuario);

}
