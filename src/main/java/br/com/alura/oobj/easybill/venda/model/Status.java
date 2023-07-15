package br.com.alura.oobj.easybill.venda.model;

import jakarta.persistence.Embeddable;

public enum Status {
    REALIZADA,
    PAGA,
    CONFIRMADA,
    FINALIZADA;
}
