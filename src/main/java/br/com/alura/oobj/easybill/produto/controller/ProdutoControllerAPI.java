package br.com.alura.oobj.easybill.produto.controller;

import br.com.alura.oobj.easybill.produto.dto.DadosProduto;
import br.com.alura.oobj.easybill.produto.dto.DadosNovoProduto;
import br.com.alura.oobj.easybill.produto.dto.DadosProdutoPaginado;
import br.com.alura.oobj.easybill.produto.model.Produto;
import br.com.alura.oobj.easybill.produto.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

