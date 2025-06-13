package co.com.restaurantediegusto.RestauranteDiegusto.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import  lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@Entity
@Table(name = "restaurantes")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Restaurante {
    @Id
    private String id;
//usa @column para mapear el campo "name" en al columna de "nombre"
    @Column(name = "nombre")
    private String name;
//usa el @Olumn para mapear el camciudadpo city a la columna ""
   @Column(name = "ciudad")
    private String city;//cambiado de state a city para ser mas claros

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)//un restaurante para muchas mesas
    @JsonManagedReference("restaurante-mesas")
    private List<Producto> productos;

  @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)//un restaurante para muchas mesas
  @JsonManagedReference("restaurante-mesas")
    private List<Mesa> mesas;
}
