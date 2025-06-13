package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Mesa;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryMesaService implements MesaRepository {
    private final List<Mesa> mesas = new ArrayList<>();

    @Override
    public List<Mesa> findByDisponibleTrue() {
        return mesas.stream().filter(Mesa::isDisponible).toList();
    }

    @Override
    public List<Mesa> findByRestauranteId(String restauranteId) {
        return List.of();
    }

    @Override
    public List<Mesa> getMesas() {
        return List.of();
    }

    @Override
    public Mesa getMesaById(String id) {
        return null;
    }

    @Override
    public Mesa createMesa(Mesa mesa) {
        return null;
    }

    @Override
    public Mesa updateMesa(String id, Mesa Mesa) {
        return null;
    }

    @Override
    public void deleteMesa(String id) {

    }
}

