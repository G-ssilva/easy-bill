package br.com.alura.oobj.easybill.repository;

import br.com.alura.oobj.easybill.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
