package br.com.jobbackenddeveloper.jobbackenddeveloper.repository;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded.PerfilEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
    Optional<Cliente> findByUsuario(String usuario);

    Optional<Cliente> findByPerfil(PerfilEnum perfil);

    boolean existsByNome(String nome);
}
