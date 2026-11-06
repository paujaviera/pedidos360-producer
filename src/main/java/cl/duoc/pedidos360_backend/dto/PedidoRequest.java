package cl.duoc.pedidos360_backend.dto;

import jakarta.validation.constraints.NotBlank;

public record PedidoRequest(@NotBlank(message = "El producto es obligatorio") String producto) {}