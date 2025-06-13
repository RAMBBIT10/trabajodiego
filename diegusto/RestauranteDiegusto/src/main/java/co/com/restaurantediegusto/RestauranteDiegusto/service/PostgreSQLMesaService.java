package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Mesa;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
@AllArgsConstructor
public class PostgreSQLMesaService implements MesaRepository  {
    private SpringDataMesaRepository repo;
    private PostgreSQLRestauranteService restauranteService;

    @Override
    public List<Mesa> getMesas() {
        return repo.findAll();
    }

    @Override
    public List<Mesa> findByDisponibleTrue() {
        return repo.findByDisponibleTrue();
    }

    @Override
    public List<Mesa> findByRestauranteId(String restauranteId) {
        return restauranteService.getRestauranteById(restauranteId).getMesas();
    }


    @Override
    public Mesa getMesaById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
    }

    @Override
    public Mesa createMesa(Mesa mesa) {
        if (repo.existsById(mesa.getId())) {
            throw new IllegalArgumentException(
                    "A user with the same ID already exists.");
        }
        return repo.save(mesa);
    }
    @Override
    public Mesa updateMesa(String id, Mesa mesa) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setName(mesa.getName());
                    existing.setEstado(mesa.isEstado());
                    existing.setDisponible(mesa.isDisponible());
                    return repo.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
    }
    @Override
    public void deleteMesa(String id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Restaurante con ID " + id + " no encontrado.");
        }
        repo.deleteById(id);
    }
}













