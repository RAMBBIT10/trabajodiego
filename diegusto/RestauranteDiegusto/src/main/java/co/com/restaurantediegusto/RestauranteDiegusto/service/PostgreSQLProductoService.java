package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Mesa;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Producto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
@AllArgsConstructor
public class PostgreSQLProductoService implements ProductoRepository {
    private SpringDataProductoRespository repo;
    private PostgreSQLRestauranteService restauranteService;

    @Override
    public List<Producto> findByRestauranteId(String restauranteId) {
        return restauranteService.getRestauranteById(restauranteId).getProductos();
    }

    @Override
    public List<Producto> getProducto() {
        return repo.findAll();
    }

    @Override
    public Producto getProductoById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto not found with id " + id));
    }

    @Override
    public Producto createProducto(Producto producto) {
        if (repo.existsById(producto.getId())) {
            throw new IllegalArgumentException(
                    "A Restaurant with the same ID already exists.");
        }
        return repo.save(producto);
    }

    @Override
    public Producto updateProducto(String id, Producto producto) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setNombre(producto.getNombre());
                    existing.setPrecio(producto.getPrecio());
                    existing.setCategoria(producto.getCategoria());
                    return repo.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
    }

    @Override
    public void deleteProducto(String id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Producto con ID " + id + " no encontrado.");
        }
        repo.deleteById(id);

    }
}

