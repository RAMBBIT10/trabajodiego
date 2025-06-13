package co.com.restaurantediegusto.RestauranteDiegusto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name="productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @NotNull(message = "Id is required")
    @Min(value = 0, message = "The Id cannot be less than 0")
    private String id;

    @NotNull(message = "Nombre is required")
    @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters")
    private String nombre;

    @NotNull(message = "Precio is required")
    @Min(value = 0, message = "The precio cannot be less than 0")
    private Integer precio;

    @ManyToOne(fetch = FetchType.LAZY) //muchas mesas pertenecena  un restaurante
    @JoinColumn(name = "restaurante_id", nullable = false )
    @JsonBackReference//evita buclesninfinitos al convertir JSON
    private Restaurante restaurante;

    @NotNull(message = "Categoria is required")
    private String categoria;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<PedidoProducto> pedido;
}