package br.com.alura.oobj.easybill.produto.repository;

import br.com.alura.oobj.easybill.classe_fiscal.model.ClasseFiscal;
import br.com.alura.oobj.easybill.cliente.model.Cliente;
import br.com.alura.oobj.easybill.produto.model.Produto;
import br.com.alura.oobj.easybill.relatorios.dto.VendaPorProduto;
import br.com.alura.oobj.easybill.venda.model.ItemVenda;
import br.com.alura.oobj.easybill.venda.model.Status;
import br.com.alura.oobj.easybill.venda.model.Venda;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void deveriaRetonarRelatorioDeVendaPorProduto(){
        Produto produto = cadastrarProduto("Coca-Cola Zero", "https://m.media-amazon.com/images/I/7117vbuFciL._AC_SY500_.jpg", "Coca-Cola Zero Lata 310 ML",
                new BigDecimal("10.00"),new BigDecimal("5.90"),"2202.10.00", null);
        Cliente cliente = cadastrarCliente("Vinícius Carvalho Zanetti", "90172575397", "(85) 98919-7344", "vinicius.zanetti@geradornv.com.br",
                "Rua Marechal Napion", "S/N", "S/C", "Barra do Ceará", "Fortaleza", "CE");
        Venda venda = cadastrarVenda(LocalDateTime.now(), Status.REALIZADA, cliente);
        cadastrarItemVenda(10, "Sem sal.", new BigDecimal("10.00"), new BigDecimal("5.90"), venda, produto);

        List<VendaPorProduto> vendaPorProdutos = produtoRepository.quantidadeVendasPorProduto();

        Assertions.assertNotNull(vendaPorProdutos);
        Assertions.assertEquals(1, vendaPorProdutos.size());
        Assertions.assertEquals("Coca-Cola Zero", vendaPorProdutos.get(0).getNomeProduto());
        Assertions.assertEquals(10, vendaPorProdutos.get(0).getQuantidadeVendas());
    }

    //Obrigatórios: nome, urlImagem, preco, classeFiscal
    private Produto cadastrarProduto(String nome, String urlImagem, String descricao,
                                     BigDecimal preco, BigDecimal precoPromocional,
                                    String classeFiscal, ClasseFiscal classeFiscalCompleta){
        Produto produto = new Produto(nome, urlImagem, descricao, preco, precoPromocional, classeFiscal, classeFiscalCompleta);
        em.persist(produto);
        return produto;
    }

    //Obrigatórios: todos
    private Cliente cadastrarCliente(String nome, String cpf, String telefone, String email, String rua, String numero,
                                     String complemento, String bairro, String cidade, String estado){
        Cliente cliente = new Cliente(nome, cpf, telefone, email, rua, numero, complemento, bairro, cidade, estado);
        em.persist(cliente);
        return cliente;
    }

    //Obrigatórios: todos
    private Venda cadastrarVenda(LocalDateTime dataHoraVenda, Status status, Cliente cliente){
        Venda venda = new Venda(dataHoraVenda, status, cliente);
        em.persist(venda);
        return venda;
    }

    //Obrigatórios: quantidade, precoUnitario, venda, produto
    private ItemVenda cadastrarItemVenda(int quantidade, String observacao, BigDecimal precoUnitario,
                                         BigDecimal precoUnitarioPromocional, Venda venda, Produto produto){
        ItemVenda itemVenda = new ItemVenda(quantidade, observacao, precoUnitario, precoUnitarioPromocional, venda, produto);
        em.persist(itemVenda);
        return itemVenda;
    }

}