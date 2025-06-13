package co.com.restaurantediegusto.RestauranteDiegusto.usecase;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import co.com.restaurantediegusto.RestauranteDiegusto.service.RestauranteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RestauranteUsecase {
    private final RestauranteRepository restauranteRepository;

    public List<Restaurante> obtenerRestaurantes() {
        return restauranteRepository.getRestaurantes();
    }

    public Restaurante obtenerPorId(String id) {
        return restauranteRepository.getRestauranteById(id);
    }

    public Restaurante guardar(Restaurante restaurante) {
        return restauranteRepository.createRestaurante(restaurante);
    }


    public void eliminar(String id) {
        restauranteRepository.deleteRestaurante(id);
    }
}
