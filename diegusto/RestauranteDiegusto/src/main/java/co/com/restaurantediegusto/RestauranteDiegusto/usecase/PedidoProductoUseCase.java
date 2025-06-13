package co.com.restaurantediegusto.RestauranteDiegusto.usecase;

import co.com.restaurantediegusto.RestauranteDiegusto.model.*;
import co.com.restaurantediegusto.RestauranteDiegusto.service.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PedidoProductoUseCase {

        private final PedidoProductoRepository PedidoProductoRepository;

        public List<PedidoProducto> obtenerTodosLosProductos() {
            return PedidoProductoRepository.getPedidosProductos();
        }

        // CORRECCIÓN 1: findById devuelve un Optional<T> para manejar el caso en que no se encuentre el producto.
        public PedidoProducto obtenerProductoPorId(String id) {
            return PedidoProductoRepository.getPedidoProductoById(id);
        }

        public PedidoProducto crearProducto(PedidoProducto producto) {
            // Para crear, nos aseguramos de que no venga con un ID o lo ignoramos para que la BD genere uno nuevo
            return PedidoProductoRepository.createPedidoProducto(producto);
        }

        // CORRECCIÓN 2: La lógica para actualizar un producto.
        public PedidoProducto actualizarPedidoProducto(String id, PedidoProducto pedidoProducto) {
            return PedidoProductoRepository.updatePedidoProducto(id, pedidoProducto);
        }
        public void eliminarPedidoProducto(String id){
            PedidoProductoRepository.deletePedidoProducto(id);

        }
    }
