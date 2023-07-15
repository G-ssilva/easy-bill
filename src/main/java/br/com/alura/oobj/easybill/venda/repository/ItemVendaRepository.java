package br.com.alura.oobj.easybill.venda.repository;

import br.com.alura.oobj.easybill.venda.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda,Long> {
    List<ItemVenda> findAllByVendaId(long id);
}
