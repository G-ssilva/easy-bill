package br.com.alura.oobj.easybill.relatorios.controller;

import br.com.alura.oobj.easybill.produto.repository.ProdutoRepository;
import br.com.alura.oobj.easybill.relatorios.dto.VendaPorProduto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin/relatorios")
@SecurityRequirement(name = "bearer-key")
public class RelatoriosControllerAPI {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("vendas-por-produto")
    public List<VendaPorProduto> vendasPorProduto(){
        return produtoRepository.quantidadeVendasPorProduto().stream().toList();
    }
}
