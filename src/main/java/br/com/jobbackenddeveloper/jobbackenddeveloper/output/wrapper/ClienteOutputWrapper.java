package br.com.jobbackenddeveloper.jobbackenddeveloper.output.wrapper;

import br.com.jobbackenddeveloper.jobbackenddeveloper.output.ClienteOutput;

public class ClienteOutputWrapper extends Wrapper {

    private ClienteOutput cliente;

    public ClienteOutputWrapper() {

    }

    public ClienteOutput getCliente() {
        return cliente;
    }

    public void setCliente(ClienteOutput cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "ClienteOutputWrapper{" +
                "cliente=" + cliente +
                '}';
    }
}
