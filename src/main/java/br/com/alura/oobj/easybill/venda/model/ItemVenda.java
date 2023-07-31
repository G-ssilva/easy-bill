package br.com.alura.oobj.easybill.venda.model;

import br.com.alura.oobj.easybill.produto.model.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Min(1)
    private int quantidade;

    @Size(max = 500)
    private String observacao;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precoUnitario;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precoUnitarioPromocional;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Venda venda;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    public ItemVenda(@NotNull int quantidade, String observacao, @NotNull BigDecimal precoUnitario, BigDecimal precoUnitarioPromocional, @NotNull Venda venda, @NotNull Produto produto) {
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.precoUnitario = precoUnitario;
        this.precoUnitarioPromocional = precoUnitarioPromocional;
        this.venda = venda;
        this.produto = produto;
    }

    public ItemVenda() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getPrecoUnitarioPromocional() {
        return precoUnitarioPromocional;
    }

    public void setPrecoUnitarioPromocional(BigDecimal precoUnitarioPromocional) {
        this.precoUnitarioPromocional = precoUnitarioPromocional;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
