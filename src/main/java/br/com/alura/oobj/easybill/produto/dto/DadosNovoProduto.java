package br.com.alura.oobj.easybill.produto.dto;

import br.com.alura.oobj.easybill.produto.model.Produto;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class DadosNovoProduto {
    @NotBlank
    @Size(max = 150)
    private String nomeProduto;

    @NotBlank
    private String preco;
    private String precoPromocional;

    @NotBlank
    @Pattern(regexp = "^\\d{4}\\.\\d{2}\\.\\d{2}$")
    private String classeFiscal;

    @Size(max = 1000)
    private String descricao;

    @NotBlank
    @Size(max = 500)
    private String urlImagem;

    public Produto toProduto() {
        Produto produto = new Produto();

        double precoDouble = Double.parseDouble(preco);
        if(precoDouble <= 0){
            throw new RuntimeException("Valor do produto deve sair mair do que zero");
        }

        if(StringUtils.isNotBlank(precoPromocional)){
            double precoPromocionalDouble = Double.parseDouble(precoPromocional);
            if(precoPromocionalDouble <= 0 || precoPromocionalDouble >= precoDouble){
                throw new RuntimeException("Preço promocional menor do que zero ou maior/igual do preço original");
            }
            produto.setPrecoPromocional(new BigDecimal(precoPromocional));
        }

        produto.setNome(nomeProduto);
        produto.setPreco(new BigDecimal(preco));
        produto.setClasseFiscal(classeFiscal);
        produto.setDescricao(descricao);
        produto.setUrlImagem(urlImagem);

        return produto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(String precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public void setClasseFiscal(String classeFiscal) {
        this.classeFiscal = classeFiscal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
