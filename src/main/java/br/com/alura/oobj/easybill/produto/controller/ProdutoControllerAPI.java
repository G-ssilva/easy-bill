package br.com.alura.oobj.easybill.produto.controller;

import br.com.alura.oobj.easybill.produto.dto.DadosNovoProduto;
import br.com.alura.oobj.easybill.produto.dto.DadosProduto;
import br.com.alura.oobj.easybill.produto.dto.DadosProdutoPaginado;
import br.com.alura.oobj.easybill.produto.model.Produto;
import br.com.alura.oobj.easybill.produto.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Cacheable(value = "listarProdutos")
    public DadosProdutoPaginado listarPorPaginacaoOrdenacao(@RequestParam("pagina") Optional<Integer> pagina){
        if(pagina.isEmpty()){
            pagina = Optional.of(0);
        }
        int numeroPagina = pagina.get();

        Pageable pageable = PageRequest.of(numeroPagina, 3, Sort.by(Sort.Direction.ASC,"nome"));
        Page<Produto> dadosProdutoPage = repository.findAll(pageable);

        List<DadosProduto> dadosProdutos = dadosProdutoPage.stream().map(DadosProduto::new).toList();
        DadosProdutoPaginado dadosProdutoPaginado = new DadosProdutoPaginado(dadosProdutos);

        dadosProdutoPaginado.setPaginaAtual(dadosProdutoPage.getNumber());
        dadosProdutoPaginado.setTotalElementos(dadosProdutoPage.getTotalElements());
        dadosProdutoPaginado.setPaginasTotais(dadosProdutoPage.getTotalPages() - 1);

        return dadosProdutoPaginado;
    }

    @PostMapping("/admin/produtos")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DadosNovoProduto> cadastrar(@RequestBody @Valid DadosNovoProduto novoProduto, UriComponentsBuilder uriBuilder){
        Produto produto = novoProduto.toProduto();
        repository.save(produto);

        URI uri = uriBuilder.path("api/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(novoProduto);
    }

    @PutMapping("/admin/produtos/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public void atualizar(@RequestBody @Valid DadosNovoProduto dadosProdutoModificado, @PathVariable long id){
        var produto = repository.getReferenceById(id);
        Produto produtoModificado = dadosProdutoModificado.toProduto();
        produto.atualizarInformacoes(produtoModificado);
    }


    @GetMapping("produtos/{id}")
    public Optional<DadosProduto> listarPorId(@PathVariable long id){
        return repository.findById(id).map(DadosProduto::new);
    }

    @DeleteMapping("admin/produtos/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public void deletar(@PathVariable long id){
        repository.deleteById(id);
    }

    @GetMapping("aW52YWxpZGEgY2FjaGUgbGlzdGFnZW0gcHJvZHV0b3M")
    @CacheEvict(value = "listarProdutos", allEntries = true)
    public void invalidarCache(){
    }

}

