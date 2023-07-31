package br.com.alura.oobj.easybill.security;

public class DadosTokenJWT {
    String token;

    public DadosTokenJWT(String token) {
        this.token = token;
    }

    public DadosTokenJWT() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
