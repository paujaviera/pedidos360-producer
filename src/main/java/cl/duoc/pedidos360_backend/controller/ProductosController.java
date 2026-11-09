package cl.duoc.pedidos360_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {

    @GetMapping
    public List<Map<String, Object>> obtenerProductos() {
        return List.of(
            Map.of("id", 1, "nombre", "Teclado RGB Mecánico", "categoria", "Periféricos", "precio", 59990, "imagen", "teclado-rgb"),
            Map.of("id", 2, "nombre", "Mouse Gamer", "categoria", "Periféricos", "precio", 34990, "imagen", "mouse-gamer"),
            Map.of("id", 3, "nombre", "Monitor Gaming 24\"", "categoria", "Monitores", "precio", 189990, "imagen", "monitor-gaming"),
            Map.of("id", 4, "nombre", "Audífonos Gamer", "categoria", "Audio", "precio", 49990, "imagen", "audifonos"),
            Map.of("id", 5, "nombre", "Disco Duro Externo 1TB", "categoria", "Almacenamiento", "precio", 64990, "imagen", "disco-duro-externo"),
            Map.of("id", 6, "nombre", "SSD 1TB", "categoria", "Almacenamiento", "precio", 79990, "imagen", "ssd"),
            Map.of("id", 7, "nombre", "Mouse Pad Gaming XL", "categoria", "Accesorios", "precio", 24990, "imagen", "mouse-pad"),
            Map.of("id", 8, "nombre", "Silla Ergonómica", "categoria", "Muebles", "precio", 129990, "imagen", "silla-ergonomica"),
            Map.of("id", 9, "nombre", "Hub USB de 4 Puertos", "categoria", "Accesorios", "precio", 19990, "imagen", "hub-usb"),
            Map.of("id", 10, "nombre", "Joystick Gamer", "categoria", "Gaming", "precio", 44990, "imagen", "joystick")
        );
    }
}