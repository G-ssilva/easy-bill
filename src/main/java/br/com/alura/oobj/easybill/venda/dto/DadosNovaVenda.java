package br.com.alura.oobj.easybill.venda.dto;

import br.com.alura.oobj.easybill.produto.repository.ProdutoRepository;
import br.com.alura.oobj.easybill.venda.model.ItemVenda;
import br.com.alura.oobj.easybill.venda.model.Status;
import br.com.alura.oobj.easybill.venda.model.Venda;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DadosNovaVenda {
    @NotNull
    private long clienteId;

    @NotNull
    private List<DadosNovoItemVenda> itens;

    public Venda toVenda(){
        Venda venda = new Venda();

        venda.setDataHoraVenda(LocalDateTime.now());
        venda.setStatus(Status.REALIZADA);

        return venda;
    }

     public List<ItemVenda> toItem(Venda venda, ProdutoRepository produtoRepository){
        DadosNovoItemVenda dadosNovoItemVenda = new DadosNovoItemVenda();
        return dadosNovoItemVenda.toItemDelegado(venda, produtoRepository, itens);
     }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public List<DadosNovoItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<DadosNovoItemVenda> itens) {
        this.itens = itens;
    }
}
