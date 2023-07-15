package br.com.alura.oobj.easybill.venda.controller;

import br.com.alura.oobj.easybill.cliente.repository.ClienteRepository;
import br.com.alura.oobj.easybill.produto.repository.ProdutoRepository;
import br.com.alura.oobj.easybill.venda.dto.DadosItens;
import br.com.alura.oobj.easybill.venda.dto.DadosNovaVenda;
import br.com.alura.oobj.easybill.venda.dto.DadosVenda;
import br.com.alura.oobj.easybill.venda.model.ItemVenda;
import br.com.alura.oobj.easybill.venda.model.Venda;
import br.com.alura.oobj.easybill.venda.repository.ItemVendaRepository;
import br.com.alura.oobj.easybill.venda.repository.VendaRepository;
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
@RequestMapping("api/vendas")
public class VendaControllerAPI {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosNovaVenda> cadastrar(@RequestBody @Valid DadosNovaVenda novaVenda, UriComponentsBuilder uriBuilder){

        Venda venda = novaVenda.toVenda();
        venda.setCliente(clienteRepository.getReferenceById(novaVenda.getClienteId()));
        List<ItemVenda> itens = novaVenda.toItem(venda, produtoRepository);

        this.vendaRepository.save(venda);
        this.itemVendaRepository.saveAll(itens);


        URI uri = uriBuilder.path("api/vendas/{id}").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).body(novaVenda);
    }

    @GetMapping("{id}")
    public DadosVenda listarPorId(@PathVariable long id){
        Optional<DadosVenda> vendaRecuperada = vendaRepository.findById(id).map(DadosVenda::new);
        if(vendaRecuperada.isEmpty()){
            throw new RuntimeException("Venda não existe");
        }
        DadosVenda dadosVenda = vendaRecuperada.get();
        List<DadosItens> dadosItemVenda = itemVendaRepository.findAllByVendaId(id).stream().map(DadosItens::new).toList();
        dadosVenda.setItens(dadosItemVenda);
        return dadosVenda;
    }


}
