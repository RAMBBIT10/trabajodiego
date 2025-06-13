package co.com.restaurantediegusto.RestauranteDiegusto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;


    @Min(value = 0, message = "El descuento no puede ser menor de 0%")
    @Max(value = 10, message = "El descuento no puede ser mayor del 10%")
    private double descuento;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    @JsonBackReference
    private Mesa mesa;


    @Transient
    public double getTotal() {
        if (items == null || items.isEmpty()) return 0;

        double subtotal = items.stream()
                .mapToDouble(p -> p.getPrecio())
                .sum();

        double descuentoAplicado = subtotal * (descuento / 100.0);
        return subtotal - descuentoAplicado;
    }

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    @Column
    private Double total; // Se calculará solo al cerrar el pedido

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("pedido-productos")
    private List<PedidoProducto> items;



}

