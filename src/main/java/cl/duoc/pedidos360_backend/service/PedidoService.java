package cl.duoc.pedidos360_backend.service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cl.duoc.pedidos360_backend.dto.PedidoRequest;
import cl.duoc.pedidos360_backend.messaging.event.PedidoEvent;
import cl.duoc.pedidos360_backend.messaging.producer.PedidoProducer;

@Service
public class PedidoService {

    private final PedidoProducer producer;
    private final AtomicLong secuencia = new AtomicLong(2);
    private final List<Map<String, Object>> pedidos = new CopyOnWriteArrayList<>(List.of(
            Map.of("id", 1L, "producto", "Notebook", "estado", "EN_PREPARACION"),
            Map.of("id", 2L, "producto", "Telefono", "estado", "DISPONIBLE")));

    public PedidoService(PedidoProducer producer) {
        this.producer = producer;
    }

    public List<Map<String, Object>> listar() {
        return pedidos;
    }

    public Map<String, Object> crear(PedidoRequest request) {
        long id = secuencia.incrementAndGet();
        Map<String, Object> pedido = Map.of(
                "id", id, "producto", request.producto(), "estado", "CREADO");
        pedidos.add(pedido);
        producer.publicarPedidoCreado(new PedidoEvent(id, request.producto(), "CREADO", Instant.now()));
        return pedido;
    }

    public Map<String, Object> cambiarEstado(long id, String nuevoEstado) {
        for (int i = 0; i < pedidos.size(); i++) {
            Map<String, Object> actual = pedidos.get(i);
            if (((Number) actual.get("id")).longValue() == id) {
                String producto = (String) actual.get("producto");
                Map<String, Object> actualizado = Map.of(
                        "id", id, "producto", producto, "estado", nuevoEstado);
                pedidos.set(i, actualizado);
                producer.publicarEstadoCambiado(new PedidoEvent(id, producto, nuevoEstado, Instant.now()));
                return actualizado;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido " + id + " no existe");
    }
}