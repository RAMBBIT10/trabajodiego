package co.com.restaurantediegusto.RestauranteDiegusto.controller;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Producto;
import co.com.restaurantediegusto.RestauranteDiegusto.usecase.ProductoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes/{restauranteId}/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoUseCase productoUseCase;

    // CREATE
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoUseCase.crearProducto(producto);
        return ResponseEntity.status(201).body(nuevoProducto);
    }

    // READ (Todos)
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos(String restauranteId) {
        return ResponseEntity.ok(productoUseCase.obtenerTodosLosProductos(restauranteId));
    }

    // READ (Uno por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable String id) {
        return ResponseEntity.ok(productoUseCase.obtenerProductoPorId(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable String id, @RequestBody Producto producto) {
        try {
            Producto productoActualizado = productoUseCase.updateProducto(id, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        try {
            productoUseCase.eliminarProducto(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}