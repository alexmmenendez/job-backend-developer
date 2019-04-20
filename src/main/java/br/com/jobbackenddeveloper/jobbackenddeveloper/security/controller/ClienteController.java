package br.com.jobbackenddeveloper.jobbackenddeveloper.security.controller;


import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded.PerfilEnum;
import br.com.jobbackenddeveloper.jobbackenddeveloper.repository.ClienteRepository;
import br.com.jobbackenddeveloper.jobbackenddeveloper.security.controller.response.Response;
import br.com.jobbackenddeveloper.jobbackenddeveloper.security.input.ClienteInput;
import br.com.jobbackenddeveloper.jobbackenddeveloper.util.SenhaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/new-client")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Response<Void>> novoClient(@Valid @RequestBody ClienteInput clienteInput,
													 BindingResult result) throws AuthenticationException {
		if (result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try {
			Cliente cliente = new Cliente();
			cliente.setNome(clienteInput.getNome());
			cliente.setUsuario(clienteInput.getUsuario());
			cliente.setPerfil(PerfilEnum.ROLE_CLIENT);
			cliente.setSenha(SenhaUtils.gerarBCrypt(clienteInput.getSenha()));
			clienteRepository.save(cliente);
			
		} catch (DataIntegrityViolationException e) {
			 new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
