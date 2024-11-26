package com.simulado.simuladoProva.repository;

import com.simulado.simuladoProva.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
