package br.com.jobbackenddeveloper.jobbackenddeveloper.controller;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded.ResponseError;
import br.com.jobbackenddeveloper.jobbackenddeveloper.output.ClienteOutput;
import br.com.jobbackenddeveloper.jobbackenddeveloper.output.ResponseErrorOutput;
import br.com.jobbackenddeveloper.jobbackenddeveloper.service.ClienteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/cliente")
public class ClienteAPI {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDocumentoByID(@PathVariable String id) {

        if (!StringUtils.isNumeric(id)){
            return new ResponseEntity<>(new ResponseErrorOutput(ResponseError.NOT_NUMERIC), HttpStatus.BAD_REQUEST);
        }

        Cliente cliente = clienteService.buscarPeloId(id);

        if (cliente == null) {
            return new ResponseEntity<>(new ResponseErrorOutput(ResponseError.NOT_FOUND), HttpStatus.BAD_REQUEST);
        }

        ClienteOutput clienteOutput = new ClienteOutput(cliente);

        return new ResponseEntity<>(clienteOutput, HttpStatus.OK);
    }
}
