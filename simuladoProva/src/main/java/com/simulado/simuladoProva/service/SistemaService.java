package com.simulado.simuladoProva.service;

import com.simulado.simuladoProva.dto.PedidoDTO;
import com.simulado.simuladoProva.dto.ProdutoDTO;
import com.simulado.simuladoProva.model.Pedido;
import com.simulado.simuladoProva.model.Produto;
import com.simulado.simuladoProva.repository.PedidoRepository;
import com.simulado.simuladoProva.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SistemaService {

    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public SistemaService(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    // Produtos
    public List<ProdutoDTO> listarProdutos() {
        return produtoRepository.findAll().stream()
                .map(produto -> new ProdutoDTO(produto.getNome(), produto.getPreco(), produto.getEstoque()))
                .collect(Collectors.toList());
    }

    public ProdutoDTO adicionarProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setEstoque(produtoDTO.getEstoque());

        Produto produtoSalvo = produtoRepository.save(produto);
        return new ProdutoDTO(produtoSalvo.getNome(), produtoSalvo.getPreco(), produtoSalvo.getEstoque());
    }

    public ProdutoDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return new ProdutoDTO(produto.getNome(), produto.getPreco(), produto.getEstoque());
    }

    // Pedidos
    public PedidoDTO adicionarPedido(PedidoDTO pedidoDTO) {
        List<Produto> produtos = pedidoDTO.getIdsProdutos().stream()
                .map(id -> produtoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: ID " + id)))
                .collect(Collectors.toList());

        Pedido pedido = new Pedido();
        pedido.setNomeCliente(pedidoDTO.getNomeCliente());
        pedido.setProdutos(produtos);
        pedido.setPrecoTotal(produtos.stream().mapToDouble(Produto::getPreco).sum());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return new PedidoDTO(
                pedidoSalvo.getNomeCliente(),
                pedidoSalvo.getProdutos().stream().map(Produto::getId).collect(Collectors.toList()),
                pedidoSalvo.getPrecoTotal()
        );
    }

    public PedidoDTO atualizarPedido(Long id, PedidoDTO pedidoDTO) {
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        List<Produto> produtos = pedidoDTO.getIdsProdutos().stream()
                .map(produtoId -> produtoRepository.findById(produtoId)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: ID " + produtoId)))
                .collect(Collectors.toList());

        pedidoExistente.setNomeCliente(pedidoDTO.getNomeCliente());
        pedidoExistente.setProdutos(produtos);
        pedidoExistente.setPrecoTotal(produtos.stream().mapToDouble(Produto::getPreco).sum());

        Pedido pedidoAtualizado = pedidoRepository.save(pedidoExistente);

        return new PedidoDTO(
                pedidoAtualizado.getNomeCliente(),
                pedidoAtualizado.getProdutos().stream().map(Produto::getId).collect(Collectors.toList()),
                pedidoAtualizado.getPrecoTotal()
        );
    }

    public void removerPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado: ID " + id);
        }
        pedidoRepository.deleteById(id);
    }
}
