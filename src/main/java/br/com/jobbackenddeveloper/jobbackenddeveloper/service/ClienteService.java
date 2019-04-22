package br.com.jobbackenddeveloper.jobbackenddeveloper.service;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.repository.ClienteRepository;
import br.com.jobbackenddeveloper.jobbackenddeveloper.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "clientes")
public class ClienteService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ClienteRepository repository;

    @Cacheable(key="#id", unless="#result == null")
    public Cliente buscarPeloId(String id) {
        simulateSlowService();

        Optional<Cliente> cliente = repository.findById(Long.parseLong(id));

        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }
    }

    @CachePut()
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    @CacheEvict( allEntries=true)
    public void deletar(Cliente cliente) {
        repository.delete(cliente);
    }

   //Simula um sistema sobrecarregado
    private void simulateSlowService() {
        try {
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public Cliente getClienteByToken(String token) throws IllegalAccessException {

        String usuario = jwtTokenUtil.getUsernameFromToken(token);

        if (usuario == null) {
            throw new IllegalAccessException();
        }
        Optional<Cliente> cliente = repository.findByUsuario(usuario);

        return cliente.get();
    }
}
