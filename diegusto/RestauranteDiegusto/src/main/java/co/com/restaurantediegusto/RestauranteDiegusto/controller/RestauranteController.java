package co.com.restaurantediegusto.RestauranteDiegusto.controller;


import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import co.com.restaurantediegusto.RestauranteDiegusto.usecase.RestauranteUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {
    private final RestauranteUsecase restauranteUseCase;

    // READ: Obtener todos los restaurantes
    @GetMapping
    public ResponseEntity<List<Restaurante>> getRestaurantes() {
        return ResponseEntity.ok(restauranteUseCase.obtenerRestaurantes());
    }

    // READ: Obtener un restaurante por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestaurantePorId(@PathVariable String id) {
        return ResponseEntity.ok(restauranteUseCase.obtenerPorId(id));

    }

    // CREATE: Crear un nuevo restaurante
    @PostMapping
    public ResponseEntity<Restaurante> crearRestaurante(@RequestBody Restaurante restaurante) {
        Restaurante nuevoRestaurante = restauranteUseCase.guardar(restaurante);
        return ResponseEntity.status(201).body(nuevoRestaurante); // Devuelve 201 Created
    }

    // UPDATE: Actualizar un restaurante existente
    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> actualizarRestaurante(@PathVariable String id, @RequestBody Restaurante restaurante) {
        restaurante.setId(id); // Aseguramos que el ID del objeto coincida con el de la URL
        Restaurante restauranteActualizado = restauranteUseCase.guardar(restaurante);
        return ResponseEntity.ok(restauranteActualizado);
    }

    // DELETE: Eliminar un restaurante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRestaurante(@PathVariable String id) {
        restauranteUseCase.eliminar(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }
}
