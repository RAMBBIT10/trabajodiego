package co.com.restaurantediegusto.RestauranteDiegusto.controller;

import co.com.restaurantediegusto.RestauranteDiegusto.model.PedidoProducto;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import co.com.restaurantediegusto.RestauranteDiegusto.usecase.PedidoProductoUseCase;
import co.com.restaurantediegusto.RestauranteDiegusto.usecase.PedidoUseCase;
import co.com.restaurantediegusto.RestauranteDiegusto.usecase.RestauranteUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurante/PedidoProducto")
@RequiredArgsConstructor
public class PedidoProductoController {
    private final PedidoProductoUseCase PedidoProductoUseCase;
    @GetMapping
    public ResponseEntity<List<PedidoProducto>> getPedidoProducto() {
        return ResponseEntity.ok(PedidoProductoUseCase.obtenerTodosLosProductos());
    }

    // READ: Obtener un restaurante por su ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoProducto> getPedidoProductoPorId(@PathVariable String id) {

        return ResponseEntity.ok(PedidoProductoUseCase.obtenerProductoPorId(id));// Si no, devuelve 404 Not Found
    }

    // CREATE: Crear un nuevo restaurante
    @PostMapping
    public ResponseEntity<PedidoProducto> crearPedidoProducto(@RequestBody PedidoProducto PedidoProducto) {
        PedidoProducto pedidoProducto = PedidoProductoUseCase.crearProducto(PedidoProducto);
        return ResponseEntity.status(201).body(PedidoProducto); // Devuelve 201 Created
    }

    // UPDATE: Actualizar un restaurante existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoProducto> actualizarPedidoProducto(@PathVariable String id, @RequestBody PedidoProducto PedidoProducto) {
        PedidoProducto.setId(id); // Aseguramos que el ID del objeto coincida con el de la URL
        PedidoProducto PedidoProductoActualizado = PedidoProductoUseCase.crearProducto(PedidoProducto);
        return ResponseEntity.ok(PedidoProducto);
    }

    // DELETE: Eliminar un restaurante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedidoProducto(@PathVariable String id) {
        PedidoProductoUseCase.eliminarPedidoProducto(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }
}

