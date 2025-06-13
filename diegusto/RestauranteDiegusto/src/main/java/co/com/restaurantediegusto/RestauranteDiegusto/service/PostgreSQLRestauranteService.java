package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@AllArgsConstructor
public class PostgreSQLRestauranteService implements RestauranteRepository {
    //Aquì implementamos los metodos que tiene JpaRespository, antes estaban UseCase
    //Dejarlos aquì nos permite agregar una capa de verificacion para que no tengamos que hacerla en UseCase
    //La estructura de los metodos que necesitamos està en Restaurante repository sin implementar
    //En esta clase, implementamos los metodos que definimos en el repositorio y utilizamos el JPA para concretar
    //la transacciòn con la base datos
    private SpringDataRestauranteRepository repo;

    @Override
    public List<Restaurante> getRestaurantes() {
        return repo.findAll();
    }

    @Override
    public Restaurante getRestauranteById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante not found with id " + id));
    }

    @Override
    public Restaurante createRestaurante(Restaurante restaurante) {
        if (repo.existsById(restaurante.getId())) {
            throw new IllegalArgumentException(
                    "A Restaurant with the same ID already exists.");
        }
        return repo.save(restaurante);
    }

    @Override
    public Restaurante updateRestaurante(String id, Restaurante restaurante) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setName(restaurante.getName());
                    existing.setCity(restaurante.getCity());
                    existing.setProductos(restaurante.getProductos());
                    return repo.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
    }


    @Override
    public void deleteRestaurante(String id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Restaurante con ID " + id + " no encontrado.");
        }
        repo.deleteById(id);
    }
}




