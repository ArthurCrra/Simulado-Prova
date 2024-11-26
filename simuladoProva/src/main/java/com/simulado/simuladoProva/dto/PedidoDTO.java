package com.simulado.simuladoProva.dto;

import java.util.List;

public class PedidoDTO {
    private String nomeCliente;
    private List<Long> idsProdutos; // IDs dos produtos
    private Double precoTotal; // Apenas no retorno


    public PedidoDTO(String nomeCliente, List<Long> idsProdutos, Double precoTotal) {
        this.nomeCliente = nomeCliente;
        this.idsProdutos = idsProdutos;
        this.precoTotal = precoTotal;
    }


    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<Long> getIdsProdutos() {
        return idsProdutos;
    }

    public void setIdsProdutos(List<Long> idsProdutos) {
        this.idsProdutos = idsProdutos;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }
}
