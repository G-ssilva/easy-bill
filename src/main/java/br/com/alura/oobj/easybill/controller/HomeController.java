package br.com.alura.oobj.easybill.controller;

import br.com.alura.oobj.easybill.model.Produto;
import br.com.alura.oobj.easybill.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Arrays;
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
