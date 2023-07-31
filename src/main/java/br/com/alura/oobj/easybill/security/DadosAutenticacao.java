package br.com.alura.oobj.easybill.security;


public class DadosAutenticacao {
    private String login;
    private String senha;

    public DadosAutenticacao(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public DadosAutenticacao() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
