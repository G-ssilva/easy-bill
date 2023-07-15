package br.com.alura.oobj.easybill.produto.repository;

import br.com.alura.oobj.easybill.produto.model.Produto;
import br.com.alura.oobj.easybill.relatorios.dto.VendaPorProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    @Query("SELECT new br.com.alura.oobj.easybill.relatorios.dto.VendaPorProduto(p.nome, sum(iv.quantidade)) FROM Produto p " +
            "JOIN ItemVenda iv ON p.id = iv.produto.id " +
            "GROUP BY p.nome " +
            "ORDER BY sum(iv.quantidade) DESC")
    List<VendaPorProduto> quantidadeVendasPorProduto();
}
