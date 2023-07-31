package br.com.alura.oobj.easybill.cliente.controller;

import br.com.alura.oobj.easybill.cliente.dto.DadosCliente;
import br.com.alura.oobj.easybill.cliente.dto.DadosNovoCliente;
import br.com.alura.oobj.easybill.cliente.model.Cliente;
import br.com.alura.oobj.easybill.cliente.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
public class ClienteControllerAPI {

    @Autowired
    private ClienteRepository repository;

    @GetMapping("admin/clientes")
    @SecurityRequirement(name = "bearer-key")
    public List<DadosCliente> listar(@RequestParam("estado") Optional<String> estado){
        if(estado.isEmpty()){
            return repository.findAll().stream().map(DadosCliente::new).toList();
        }
        String estadoString = estado.get().toUpperCase();
        return repository.findByEnderecoEstado(estadoString).stream().map(DadosCliente::new).toList();
    }

    @GetMapping("api/clientes/{id}")
    public Optional<DadosCliente> listarPorId(@PathVariable long id){
        return repository.findById(id).map(DadosCliente::new);
    }

    @PostMapping("api/clientes")
    @Transactional
    public ResponseEntity<DadosNovoCliente> cadastrar(@RequestBody @Valid DadosNovoCliente novoCliente, UriComponentsBuilder uriBuilder){
        Cliente cliente = novoCliente.toCliente();
        repository.save(cliente);

        URI uri = uriBuilder.path("api/produtos/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(novoCliente);
    }

}
