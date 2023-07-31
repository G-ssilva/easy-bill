package br.com.alura.oobj.easybill.venda.dto;

import br.com.alura.oobj.easybill.produto.model.Produto;
import br.com.alura.oobj.easybill.produto.repository.ProdutoRepository;
import br.com.alura.oobj.easybill.venda.model.ItemVenda;
import br.com.alura.oobj.easybill.venda.model.Venda;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class DadosNovoItemVenda {

    @NotNull
    @Min(1)
    private int quantidade;

    @JsonAlias("observação")
    @Size(max = 500)
    private String observacao;

    @NotNull
    private long produtoId;

    public DadosNovoItemVenda(int quantidade, String observacao, long produtoId) {
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.produtoId = produtoId;
    }

    public DadosNovoItemVenda() {
    }

    public List<ItemVenda> toItemDelegado(Venda venda, ProdutoRepository produtoRepository, @NotNull List<DadosNovoItemVenda> itens) {
        List<ItemVenda> itensVenda = new ArrayList<>();

        itens.forEach(item -> {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(venda);
            itemVenda.setQuantidade(item.quantidade);
            itemVenda.setObservacao(item.observacao);
            itemVenda.setProduto(produtoRepository.getReferenceById(item.produtoId));
            itemVenda.setPrecoUnitario(itemVenda.getProduto().getPreco());
            if(itemVenda.getProduto().getPrecoPromocional() != null){
                itemVenda.setPrecoUnitarioPromocional(itemVenda.getProduto().getPrecoPromocional());
            }
            itensVenda.add(itemVenda);
        });

        return itensVenda;

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
}
