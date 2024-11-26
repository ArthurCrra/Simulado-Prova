package com.simulado.simuladoProva.repository;

import com.simulado.simuladoProva.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
