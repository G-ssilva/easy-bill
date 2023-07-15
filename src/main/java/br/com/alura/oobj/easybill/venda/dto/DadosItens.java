package br.com.alura.oobj.easybill.venda.dto;

import br.com.alura.oobj.easybill.venda.model.ItemVenda;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.util.StringUtils;

public class DadosItens {
    private long id;
    private int quantidade;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("observação")
    private String observacao;
    private long produtoId;
    private String precoUnitario;
    private String precoUnitarioPromocional = null;


    public DadosItens(ItemVenda itemVenda){
        this.id = itemVenda.getId();
        this.quantidade = itemVenda.getQuantidade();
        if(StringUtils.isNotBlank(itemVenda.getObservacao())){
            this.observacao = itemVenda.getObservacao();
        }
        this.produtoId = itemVenda.getProduto().getId();
        this.precoUnitario = itemVenda.getPrecoUnitario().toString();
        if(itemVenda.getPrecoUnitarioPromocional() != null){
            this.precoUnitarioPromocional = itemVenda.getPrecoUnitarioPromocional().toString();
        }
    }

    public DadosItens(long id, int quantidade, String observacao, long produtoId, String precoUnitario, String precoUnitarioPromocional) {
        this.id = id;
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.produtoId = produtoId;
        this.precoUnitario = precoUnitario;
        this.precoUnitarioPromocional = precoUnitarioPromocional;
    }

    public DadosItens() {
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

    public long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(long produtoId) {
        this.produtoId = produtoId;
    }

    public String getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(String precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getPrecoUnitarioPromocional() {
        return precoUnitarioPromocional;
    }

    public void setPrecoUnitarioPromocional(String precoUnitarioPromocional) {
        this.precoUnitarioPromocional = precoUnitarioPromocional;
    }
}
