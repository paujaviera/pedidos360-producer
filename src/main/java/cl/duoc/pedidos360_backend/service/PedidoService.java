package cl.duoc.pedidos360_backend.service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import cl.duoc.pedidos360_backend.dto.PedidoRequest;
import cl.duoc.pedidos360_backend.entity.Pedido;
import cl.duoc.pedidos360_backend.messaging.event.PedidoEvent;
import cl.duoc.pedidos360_backend.messaging.producer.PedidoProducer;
import cl.duoc.pedidos360_backend.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoProducer producer;
    private final PedidoRepository repository;

    public PedidoService(PedidoProducer producer, PedidoRepository repository) {
        this.producer = producer;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> listar() {
        return repository.findAll().stream().map(this::toMap).toList();
    }

    @Transactional
    public Map<String, Object> crear(PedidoRequest request) {
        Pedido p = repository.save(new Pedido(request.producto(), "CREADO"));
        producer.publicarPedidoCreado(new PedidoEvent(p.getId(), p.getProducto(), p.getEstado(), Instant.now()));
        return toMap(p);
    }

    @Transactional
    public Map<String, Object> cambiarEstado(long id, String nuevoEstado) {
        Pedido p = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido " + id + " no existe"));
        p.setEstado(nuevoEstado);
        repository.save(p);
        producer.publicarEstadoCambiado(new PedidoEvent(p.getId(), p.getProducto(), nuevoEstado, Instant.now()));
        return toMap(p);
    }

    private Map<String, Object> toMap(Pedido p) {
        return Map.of("id", p.getId(), "producto", p.getProducto(), "estado", p.getEstado());
    }
}