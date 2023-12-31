package br.com.alura.oobj.easybill.cliente.repository;

import br.com.alura.oobj.easybill.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByEnderecoEstado(String estado);
}
