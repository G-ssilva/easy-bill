package br.com.alura.oobj.easybill.controller;

import br.com.alura.oobj.easybill.dto.DadosProduto;
import br.com.alura.oobj.easybill.dto.DadosNovoProduto;
import br.com.alura.oobj.easybill.model.Produto;
import br.com.alura.oobj.easybill.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ProdutoControllerAPI {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("produtos")
    public List<DadosProduto> listar(){
        return repository.findAll().stream().map(DadosProduto::new).toList();
    }

    @GetMapping("produtos/{id}")
    public Optional<DadosProduto> listarPorId(@PathVariable long id){
        return repository.findById(id).map(DadosProduto::new);
    }

    @PostMapping("/admin/produtos")
    public ResponseEntity<DadosNovoProduto> cadastrar(@RequestBody @Valid DadosNovoProduto novoProduto, UriComponentsBuilder uriBuilder){
        Produto produto = novoProduto.toProduto();
        repository.save(produto);

        URI uri = uriBuilder.path("api/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(novoProduto);
    }

    @PutMapping("/admin/produtos/{id}")
    @Transactional
    public void atualizar(@RequestBody @Valid DadosNovoProduto dadosProdutoModificado, @PathVariable long id){
        var produto = repository.getReferenceById(id);
        Produto produtoModificado = dadosProdutoModificado.toProduto();
        produto.atualizarInformacoes(produtoModificado);
    }

    @DeleteMapping("admin/produtos/{id}")
    @Transactional
    public void deletar(@PathVariable long id){
        repository.deleteById(id);
    }

}

