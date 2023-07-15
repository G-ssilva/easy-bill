package br.com.alura.oobj.easybill.produto.dto;

import br.com.alura.oobj.easybill.produto.model.Produto;

import java.math.BigDecimal;

public class DadosProduto {
    private long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String classeFiscal;

    public DadosProduto(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        if(produto.getDescricao().length() > 250){
            this.descricao = produto.getDescricao().substring(0,149);
            this.descricao += "...";
        } else {
            this.descricao = produto.getDescricao();
        }
        if(produto.getPrecoPromocional() == null){
            this.preco = produto.getPreco();
        } else {
            this.preco = produto.getPrecoPromocional();
        }
        this.classeFiscal = produto.getClasseFiscal();
    }

    public DadosProduto() {
    }

    public DadosProduto(long id, String nome, String descricao, BigDecimal preco, String classeFiscal) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.classeFiscal = classeFiscal;
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

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public void setClasseFiscal(String classeFiscal) {
        this.classeFiscal = classeFiscal;
    }
}
