package br.com.jobbackenddeveloper.jobbackenddeveloper.service;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente buscarPeloId(String id) {
        return repository.findById(Long.parseLong(id)).get();
    }
}
