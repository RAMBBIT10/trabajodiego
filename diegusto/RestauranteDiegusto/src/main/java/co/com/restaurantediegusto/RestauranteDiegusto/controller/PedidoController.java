package co.com.restaurantediegusto.RestauranteDiegusto.controller;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import co.com.restaurantediegusto.RestauranteDiegusto.usecase.PedidoUseCase;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Pedido;
import co.com.restaurantediegusto.RestauranteDiegusto.usecase.PedidoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController

@RequestMapping("api/v1/restaurantes/{restauranteId}/mesas/{mesasId}/pedidos")
@AllArgsConstructor

public class PedidoController {
    private final PedidoUseCase pedidoUsecase;

    // Obtener todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos() {
        return ResponseEntity.ok(pedidoUsecase.obtenerTodos());
    }

    // READ: Obtener un restaurante por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoPorId(@PathVariable String id) {
        try {
            // Ahora simplemente llamamos al método. Si no encuentra el pedido,
            // el UseCase lanzará una excepción.
            Pedido pedido = pedidoUsecase.obtenerPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            // Si el UseCase lanza la excepción, la capturamos y devolvemos 404 Not Found.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado" + e);
}

    }

    // CREATE: Crear un nuevo restaurante
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido nuevoPedido) {
        Pedido nuevoRestaurante = pedidoUsecase.crearPedido(nuevoPedido);
        return ResponseEntity.status(201).body(nuevoPedido); // Devuelve 201 Created
    }

    // UPDATE: Actualizar un restaurante existente
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable String id, @RequestBody Pedido PedidoActualizado) {
        getPedidoPorId(id); // Aseguramos que el ID del objeto coincida con el de la URL
        Pedido pedidoActualizado = pedidoUsecase.obtenerPorId(id);
        return ResponseEntity.ok(pedidoActualizado);
    }

    // DELETE: Eliminar un restaurante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRestaurante(@PathVariable String id) {
        getPedidoPorId(id);
        pedidoUsecase.eliminar(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }
}

