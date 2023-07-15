package br.com.alura.oobj.easybill.cliente.model;

import br.com.alura.oobj.easybill.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(unique=true)
    private String cpf;

    @NotBlank
    private String telefone;

    @NotBlank
    @Email
    private String email;

    @Embedded
    private Endereco endereco;


    public Cliente(long id, String nome, String cpf, String telefone, String email, String rua, String numero, String complemento, String bairro, String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = new Endereco(rua, numero, complemento, bairro, cidade, estado);
    }

    public Cliente() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return this.endereco.getRua();
    }

    public void setRua(String rua) {
        this.endereco.setRua(rua);
    }

    public String getNumero() {
        return this.endereco.getNumero();
    }

    public void setNumero(String numero) {
        this.endereco.setNumero(numero);
    }

    public String getComplemento() {
        return this.endereco.getComplemento();
    }

    public void setComplemento(String complemento) {
        this.endereco.setComplemento(complemento);
    }

    public String getBairro() {
        return this.endereco.getBairro();
    }

    public void setBairro(String bairro) {
        this.endereco.setBairro(bairro);
    }

    public String getCidade() {
        return this.endereco.getCidade();
    }

    public void setCidade(String cidade) {
        this.endereco.setCidade(cidade);
    }

    public String getEstado() {
        return this.endereco.getEstado();
    }

    public void setEstado(String estado) {
        this.endereco.setEstado(estado);
    }
}
