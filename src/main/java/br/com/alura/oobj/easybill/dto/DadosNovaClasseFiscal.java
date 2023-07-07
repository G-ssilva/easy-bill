package br.com.alura.oobj.easybill.dto;

import br.com.alura.oobj.easybill.model.ClasseFiscal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static java.util.regex.Pattern.matches;

public class DadosNovaClasseFiscal {
    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{4}\\.\\d{2}\\.\\d{2}$")
    private String codigo;

    @NotBlank
    @Size(max = 250)
    private String descricao;

    public ClasseFiscal toClasseFiscal(){
        ClasseFiscal classeFiscal = new ClasseFiscal();

        classeFiscal.setCodigo(codigo);
        classeFiscal.setDescricao(descricao);

        return classeFiscal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
