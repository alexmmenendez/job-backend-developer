package br.com.jobbackenddeveloper.jobbackenddeveloper.output;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded.PerfilEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClienteOutput {

    private Long id;
    private String nome;
    private String usuario;
    private String job;
    private String hobby;
    private PerfilEnum perfil;

    public ClienteOutput() {

    }

    public ClienteOutput(Cliente cliente) {
        this.setId(cliente.getId());
        this.setNome(cliente.getNome());
        this.setUsuario(cliente.getUsuario());
        this.setPerfil(cliente.getPerfil());
        this.setJob(cliente.getJob());
        this.setHobby(cliente.getHobby());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
