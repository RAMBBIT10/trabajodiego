package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRestauranteService implements RestauranteRepository {
    private final List<Restaurante> restaurantes = new ArrayList<>();


    @Override
    public List<Restaurante> getRestaurantes() {
        return this.restaurantes;
    }

    @Override
    public Restaurante getRestauranteById(String id) {
        return restaurantes.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Override
    public Restaurante createRestaurante(Restaurante restaurante) {
        if (restaurantes.stream().anyMatch(existingRestaurantes -> existingRestaurantes.getId().equals(restaurante.getId()))) {
            throw new IllegalArgumentException("User already exists with id: " + restaurante.getId());
        }
        restaurantes.add(restaurante);
        return restaurante;
    }

    @Override
    public Restaurante updateRestaurante(String id, Restaurante restaurante) {
        Restaurante existingRestaurante = restaurantes.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        existingRestaurante.setName(restaurante.getName());
        existingRestaurante.setCity(restaurante.getCity());
        existingRestaurante.setProductos(restaurante.getProductos());
        existingRestaurante.setMesas(restaurante.getMesas());
        return existingRestaurante;
    }

    @Override
    public void deleteRestaurante(String id) {
        Restaurante restaurante = restaurantes.stream()
                .filter(existingRestaurante -> existingRestaurante.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        restaurantes.remove(restaurante);
    }

    }

