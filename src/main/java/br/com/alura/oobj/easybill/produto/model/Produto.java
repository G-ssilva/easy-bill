package br.com.alura.oobj.easybill.produto.model;

import br.com.alura.oobj.easybill.classe_fiscal.model.ClasseFiscal;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 150)
    private String nome;

    @NotNull
    @Size(max = 500)
    private String urlImagem;

    @Size(max = 1000)
    private String descricao;

    @NotNull
    private BigDecimal preco;
    private BigDecimal precoPromocional;

    @NotNull
    @Size(min = 10, max = 10)
    private String classeFiscal;

    @ManyToOne(fetch = FetchType.LAZY)
    @Valid
    private ClasseFiscal classeFiscalCompleta;

    public Produto() {
    }

    public Produto(Long id, @NotNull String nome, @NotNull String urlImagem, String descricao, @NotNull BigDecimal preco, BigDecimal precoPromocional, @NotNull String classeFiscal, ClasseFiscal classeFiscalCompleta) {
        this.id = id;
        this.nome = nome;
        this.urlImagem = urlImagem;
        this.descricao = descricao;
        this.preco = preco;
        this.precoPromocional = precoPromocional;
        this.classeFiscal = classeFiscal;
        this.classeFiscalCompleta = classeFiscalCompleta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(BigDecimal precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public void setClasseFiscal(String classeFiscal) {
        this.classeFiscal = classeFiscal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ClasseFiscal getClasseFiscalCompleta() {
        return classeFiscalCompleta;
    }

    public void setClasseFiscalCompleta(ClasseFiscal classeFiscalCompleta) {
        this.classeFiscalCompleta = classeFiscalCompleta;
    }

    public void atualizarInformacoes(Produto produtoModificado) {
        this.nome = produtoModificado.getNome();
        this.urlImagem = produtoModificado.getUrlImagem();
        this.descricao = produtoModificado.getDescricao();
        this.preco = produtoModificado.getPreco();
        this.precoPromocional = produtoModificado.getPrecoPromocional();
        this.classeFiscal = produtoModificado.getClasseFiscal();
    }
}
