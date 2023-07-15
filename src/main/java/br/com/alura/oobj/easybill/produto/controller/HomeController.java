package br.com.alura.oobj.easybill.produto.controller;

import br.com.alura.oobj.easybill.produto.model.Produto;
import br.com.alura.oobj.easybill.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("home")
    public String paginaInicial(Model model){
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);

        return "index";
    }
}
