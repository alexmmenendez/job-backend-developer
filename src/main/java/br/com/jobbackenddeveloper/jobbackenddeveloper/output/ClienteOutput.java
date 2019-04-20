package br.com.jobbackenddeveloper.jobbackenddeveloper.output;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded.PerfilEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClienteOutput {

    private String nome;
    private String usuario;
    private PerfilEnum perfil;

    public ClienteOutput() {

    }

    public ClienteOutput(Cliente cliente) {
        this.nome = cliente.getNome();
        this.usuario = cliente.getUsuario();
        this.perfil = cliente.getPerfil();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "ClienteOutput{" +
                "nome='" + nome + '\'' +
                ", usuario='" + usuario + '\'' +
                ", perfil=" + perfil +
                '}';
    }
}
