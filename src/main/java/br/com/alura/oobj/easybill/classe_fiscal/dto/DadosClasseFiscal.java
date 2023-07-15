package br.com.alura.oobj.easybill.classe_fiscal.dto;

import br.com.alura.oobj.easybill.classe_fiscal.model.ClasseFiscal;

public class DadosClasseFiscal {
    private long id;
    private String codigo;
    private String descricao;


    public DadosClasseFiscal(ClasseFiscal classeFiscal) {
        this.id = classeFiscal.getId();
        this.codigo = classeFiscal.getCodigo();
        this.descricao = classeFiscal.getDescricao();
    }

    public DadosClasseFiscal(long id, String classeFiscal, String descricao) {
        this.id = id;
        this.codigo = classeFiscal;
        this.descricao = descricao;
    }

    public DadosClasseFiscal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClasseFiscal() {
        return codigo;
    }

    public void setClasseFiscal(String classeFiscal) {
        this.codigo = classeFiscal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
