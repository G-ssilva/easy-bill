package br.com.alura.oobj.easybill.venda.repository;

import br.com.alura.oobj.easybill.venda.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda,Long> {
}
