package br.com.alura.oobj.easybill.relatorios.dto;

import br.com.alura.oobj.easybill.produto.model.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaPorProduto {
    private String nomeProduto;
    private long quantidadeVendas;

    public VendaPorProduto(String nomeProduto, long quantidadeVendas) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendas = quantidadeVendas;
    }

    public VendaPorProduto() {
    }

    public VendaPorProduto(VendaPorProduto vendaPorProduto) {
        this.nomeProduto = vendaPorProduto.getNomeProduto();
        this.quantidadeVendas = vendaPorProduto.getQuantidadeVendas();
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public long getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public void setQuantidadeVendas(long quantidadeVendas) {
        this.quantidadeVendas = quantidadeVendas;
    }
}
