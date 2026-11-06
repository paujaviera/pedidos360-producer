package cl.duoc.pedidos360_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.pedidos360_backend.dto.EstadoRequest;
import cl.duoc.pedidos360_backend.dto.PedidoRequest;
import cl.duoc.pedidos360_backend.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class Pedidos360Controller {

    private final PedidoService pedidoService;

    public Pedidos360Controller(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Map<String, Object>> obtenerPedidos() {
        return pedidoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> crearPedido(@Valid @RequestBody PedidoRequest request) {
        return pedidoService.crear(request);
    }

    @PatchMapping("/{id}/estado")
    public Map<String, Object> cambiarEstado(@PathVariable long id,
                                             @Valid @RequestBody EstadoRequest request) {
        return pedidoService.cambiarEstado(id, request.estado());
    }
}