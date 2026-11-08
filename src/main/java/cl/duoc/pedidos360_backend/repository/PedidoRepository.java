package cl.duoc.pedidos360_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.pedidos360_backend.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}