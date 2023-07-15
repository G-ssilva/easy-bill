package br.com.alura.oobj.easybill.venda.dto;

import br.com.alura.oobj.easybill.venda.model.Venda;
import br.com.alura.oobj.easybill.venda.repository.ItemVendaRepository;

import java.util.List;


public class DadosVenda {
    private long id;
    private String status;
    private String dataRealizacao;
    private long clienteId;
    private List<DadosItens> itens;

    public DadosVenda(Venda venda){
        this.id = venda.getId();
        this.status = venda.getStatus().toString();
        this.dataRealizacao = venda.getDataHoraVenda().toString();
        this.clienteId = venda.getCliente().getId();
    }

    public DadosVenda(long id, String status, String dataRealizacao, long clienteId) {
        this.id = id;
        this.status = status;
        this.dataRealizacao = dataRealizacao;
        this.clienteId = clienteId;
    }

    public DadosVenda() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(String dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public List<DadosItens> getItens() {
        return itens;
    }

    public void setItens(List<DadosItens> itens) {
        this.itens = itens;
    }
}
