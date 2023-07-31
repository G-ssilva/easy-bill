package br.com.alura.oobj.easybill.venda.controller;

import br.com.alura.oobj.easybill.classe_fiscal.model.ClasseFiscal;
import br.com.alura.oobj.easybill.cliente.model.Cliente;
import br.com.alura.oobj.easybill.cliente.repository.ClienteRepository;
import br.com.alura.oobj.easybill.produto.model.Produto;
import br.com.alura.oobj.easybill.produto.repository.ProdutoRepository;
import br.com.alura.oobj.easybill.venda.dto.DadosNovaVenda;
import br.com.alura.oobj.easybill.venda.dto.DadosNovoItemVenda;
import br.com.alura.oobj.easybill.venda.model.ItemVenda;
import br.com.alura.oobj.easybill.venda.model.Status;
import br.com.alura.oobj.easybill.venda.model.Venda;
import br.com.alura.oobj.easybill.venda.repository.ItemVendaRepository;
import br.com.alura.oobj.easybill.venda.repository.VendaRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class VendaControllerAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosNovaVenda> dadosNovaVendaJson;

    @Test
    @WithMockUser
    void validarBeanValidationQuandoOsItensForemNull() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(
                        post("/api/vendas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosNovaVendaJson.write(
                                        new DadosNovaVenda(1L, null)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

}