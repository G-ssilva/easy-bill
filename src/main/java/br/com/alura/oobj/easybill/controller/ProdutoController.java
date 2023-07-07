package br.com.alura.oobj.easybill.controller;

import br.com.alura.oobj.easybill.dto.DadosNovoProduto;
import br.com.alura.oobj.easybill.model.Produto;
import br.com.alura.oobj.easybill.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("formulario")
    public String formulario(DadosNovoProduto requisicao){
        return "produto/formulario";
    }

    @PostMapping("")
    @Transactional
    public String novoProduto(@Valid DadosNovoProduto requisicao, BindingResult result){
        if(result.hasErrors()){
            return "produto/formulario";
        }

        Produto produto = requisicao.toProduto();
        produtoRepository.save(produto);

        return "redirect:/home";
    }

    @GetMapping
    public String listarProdutos() {
        return "redirect:/home";
    }
}
