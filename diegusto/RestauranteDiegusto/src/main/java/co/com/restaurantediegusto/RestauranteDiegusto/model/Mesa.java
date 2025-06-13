package co.com.restaurantediegusto.RestauranteDiegusto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "mesas")
@Data
@AllArgsConstructor
@NoArgsConstructor

@ToString(exclude = "restaurante")
public class Mesa {
    @Id //como es un string, no puede ser generado con IDENTITY
    private String id; //combiado de int a string para que coincida

    //usar el @column para mapear el campo "name" a la columna nombre
    @Column(name = "numero")
    private String name;

    @Column(name = "estaDisponible")
    private boolean estado;

    @ManyToOne(fetch = FetchType.LAZY) //muchas mesas pertenecena  un restaurante
    @JoinColumn(name = "restaurante_id", nullable = false )
    @JsonBackReference("restaurante-mesas")//evita buclesninfinitos al convertir JSON
    private Restaurante restaurante;

    @Column(nullable = false)
    private boolean disponible = true;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)//un restaurante para muchas mesas
    @JsonManagedReference("mesa-pedidos")
    private List<Pedido> pedidos;


}
