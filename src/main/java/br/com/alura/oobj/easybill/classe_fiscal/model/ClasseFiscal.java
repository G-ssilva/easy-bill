package br.com.alura.oobj.easybill.classe_fiscal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class ClasseFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 10, max = 10)
    @Column(unique=true)
    @Pattern(regexp = "^\\d{4}\\.\\d{2}\\.\\d{2}$")
    private String codigo;

    @NotBlank
    @Size(max = 250)
    private String descricao;

    public ClasseFiscal() {
    }

    public ClasseFiscal(long id, String codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void atualizarInformacoes(ClasseFiscal classeFiscalModificada) {
        this.codigo = classeFiscalModificada.getCodigo();
        this.descricao = classeFiscalModificada.getDescricao();
    }
}
