package cl.duoc.pedidos360_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String producto;

    @Column(nullable = false)
    private String estado;

    protected Pedido() {}

    public Pedido(String producto, String estado) {
        this.producto = producto;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public String getProducto() { return producto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}