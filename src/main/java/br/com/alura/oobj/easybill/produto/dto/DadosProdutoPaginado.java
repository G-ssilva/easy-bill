package br.com.alura.oobj.easybill.produto.dto;

import java.util.List;

public class DadosProdutoPaginado {
    private List<DadosProduto> dadosProdutoList;
    private int paginaAtual;
    private long totalElementos;
    private int paginasTotais;

    public DadosProdutoPaginado(List<DadosProduto> dadosProdutoList) {
        this.dadosProdutoList = dadosProdutoList;
    }

    public List<DadosProduto> getDadosProdutoList() {
        return dadosProdutoList;
    }

    public void setDadosProdutoList(List<DadosProduto> dadosProdutoList) {
        this.dadosProdutoList = dadosProdutoList;
    }

    public int getPaginasTotais() {
        return paginasTotais;
    }

    public void setPaginasTotais(int paginasTotais) {
        this.paginasTotais = paginasTotais;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }
}
