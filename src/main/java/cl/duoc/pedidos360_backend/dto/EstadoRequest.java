package cl.duoc.pedidos360_backend.dto;

import jakarta.validation.constraints.NotBlank;

public record EstadoRequest(@NotBlank(message = "El estado es obligatorio") String estado) {}