package co.com.restaurantediegusto.RestauranteDiegusto.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Pedido_Producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProducto {

    @Id //como es un string, no puede ser generado con IDENTITY
    private String id; //combiado de int a string para que coincida

    @ManyToOne(fetch = FetchType.LAZY) //muchas mesas pertenecena  un restaurante
    @JoinColumn(name= "pedido_id")
    @JsonBackReference("pedido-productos")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY) //muchas mesas pertenecena  un restaurante
    @JoinColumn(name= "producto_id")
    @JsonBackReference("pedido-productos")
    private Producto producto;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int cantidad;


    public double getPrecio() {
        return producto.getPrecio();
    }
}



