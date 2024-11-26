package com.simulado.simuladoProva.controller;

import com.simulado.simuladoProva.dto.PedidoDTO;
import com.simulado.simuladoProva.dto.ProdutoDTO;
import com.simulado.simuladoProva.service.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sistema")
public class SistemaController {

    private final SistemaService service;

    @Autowired
    public SistemaController(SistemaService service) {
        this.service = service;
    }

    // Produtos

    @PostMapping("/produtos")
    public ProdutoDTO adicionarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return service.adicionarProduto(produtoDTO);
    }

    @GetMapping("/produtos")
    public List<ProdutoDTO> listarProdutos() {
        return service.listarProdutos();
    }

    @GetMapping("/produtos/{id}")
    public ProdutoDTO buscarProdutoPorId(@PathVariable Long id) {
        return service.buscarProdutoPorId(id);
    }

    // Pedidos

    @PostMapping("/pedidos")
    public PedidoDTO adicionarPedido(@RequestBody PedidoDTO pedidoDTO) {
        return service.adicionarPedido(pedidoDTO);
    }

    @PutMapping("/pedidos/{id}")
    public PedidoDTO atualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        return service.atualizarPedido(id, pedidoDTO);
    }

    @DeleteMapping("/pedidos/{id}")
    public void removerPedido(@PathVariable Long id) {
        service.removerPedido(id);
    }
}
