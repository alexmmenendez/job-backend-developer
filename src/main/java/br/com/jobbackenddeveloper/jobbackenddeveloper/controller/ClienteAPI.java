package br.com.jobbackenddeveloper.jobbackenddeveloper.controller;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded.PerfilEnum;
import br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded.ResponseError;
import br.com.jobbackenddeveloper.jobbackenddeveloper.output.ClienteOutput;
import br.com.jobbackenddeveloper.jobbackenddeveloper.output.ResponseErrorOutput;
import br.com.jobbackenddeveloper.jobbackenddeveloper.security.input.ClienteInput;
import br.com.jobbackenddeveloper.jobbackenddeveloper.service.ClienteService;
import br.com.jobbackenddeveloper.jobbackenddeveloper.util.SenhaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/cliente")
public class ClienteAPI extends API {

    @Autowired
    private ClienteService clienteService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/salvar")
    public ResponseEntity<?> salvarCliente(@Valid @RequestBody ClienteInput clienteInput,
                                           BindingResult result) throws AuthenticationException {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Cliente cliente = new Cliente();

        try {
            cliente.setNome(clienteInput.getNome());
            cliente.setUsuario(clienteInput.getUsuario());
            cliente.setPerfil(PerfilEnum.ROLE_CLIENT);
            cliente.setSenha(SenhaUtils.gerarBCrypt(clienteInput.getSenha()));
            cliente.setJob(clienteInput.getJob());
            cliente.setHobby(clienteInput.getHobby());
            clienteService.salvar(cliente);

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ClienteOutput clienteOutput = new ClienteOutput(cliente);

        return new ResponseEntity<>(clienteOutput, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/alterar/{id}")
    public ResponseEntity<?> alterarCliente(@PathVariable String id, @Valid @RequestBody ClienteInput clienteInput,
                                           BindingResult result) throws AuthenticationException {
/*
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
*/

        if (!StringUtils.isNumeric(id)){
            return new ResponseEntity<>(new ResponseErrorOutput(ResponseError.NOT_NUMERIC), HttpStatus.BAD_REQUEST);
        }

        Cliente cliente = clienteService.buscarPeloId(id);

        if (cliente == null) {
            return new ResponseEntity<>(new ResponseErrorOutput(ResponseError.NOT_FOUND), HttpStatus.BAD_REQUEST);
        }

        try {
            cliente.setNome(clienteInput.getNome());
            cliente.setJob(clienteInput.getJob());
            cliente.setHobby(clienteInput.getHobby());
            clienteService.salvar(cliente);

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ClienteOutput clienteOutput = new ClienteOutput(cliente);

        return new ResponseEntity<>(clienteOutput, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable String id) {

        if (!StringUtils.isNumeric(id)){
            return new ResponseEntity<>(new ResponseErrorOutput(ResponseError.NOT_NUMERIC), HttpStatus.BAD_REQUEST);
        }

        Cliente cliente = clienteService.buscarPeloId(id);

        if (cliente == null) {
            return new ResponseEntity<>(new ResponseErrorOutput(ResponseError.NOT_FOUND), HttpStatus.NOT_FOUND);
        }

        ClienteOutput clienteOutput = new ClienteOutput(cliente);

        return new ResponseEntity<>(clienteOutput, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id, HttpServletRequest request) {

        if (!StringUtils.isNumeric(id)) {
            return new ResponseEntity<>(new ResponseErrorOutput(ResponseError.NOT_NUMERIC), HttpStatus.BAD_REQUEST);
        }

        Cliente clienteByToken;

        try {
            clienteByToken = clienteService.getClienteByToken(getToken(request));
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(new ResponseErrorOutput(ResponseError.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
        }

        Cliente cliente = clienteService.buscarPeloId(id);

        if (cliente == null) {
            return new ResponseEntity<>(new ResponseErrorOutput(ResponseError.NOT_FOUND), HttpStatus.BAD_REQUEST);
        }

        if (cliente.equals(clienteByToken)) {
            return new ResponseEntity<>(new ResponseErrorOutput(ResponseError.NOT_ALLOWED), HttpStatus.BAD_REQUEST);
        }

        clienteService.deletar(cliente);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
