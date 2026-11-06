package cl.duoc.pedidos360_backend.messaging.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cl.duoc.pedidos360_backend.messaging.event.PedidoEvent;

@Component
public class PedidoProducer {

    private static final Logger log = LoggerFactory.getLogger(PedidoProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String creadoKey;
    private final String estadoKey;

    public PedidoProducer(RabbitTemplate rabbitTemplate,
                          @Value("${app.rabbit.exchange.pedidos}") String exchange,
                          @Value("${app.rabbit.routing-key.pedido-creado}") String creadoKey,
                          @Value("${app.rabbit.routing-key.pedido-estado}") String estadoKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.creadoKey = creadoKey;
        this.estadoKey = estadoKey;
    }

    public void publicarPedidoCreado(PedidoEvent evento) {
        rabbitTemplate.convertAndSend(exchange, creadoKey, evento);
        log.info("Evento publicado: pedido {} ({})", evento.id(), creadoKey);
    }

    public void publicarEstadoCambiado(PedidoEvent evento) {
        rabbitTemplate.convertAndSend(exchange, estadoKey, evento);
        log.info("Evento publicado: pedido {} ({})", evento.id(), estadoKey);
    }
}