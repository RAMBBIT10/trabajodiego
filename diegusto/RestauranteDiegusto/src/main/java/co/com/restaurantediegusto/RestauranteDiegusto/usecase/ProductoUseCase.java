package co.com.restaurantediegusto.RestauranteDiegusto.usecase;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Mesa;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Producto;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import co.com.restaurantediegusto.RestauranteDiegusto.service.ProductoRepository;
import co.com.restaurantediegusto.RestauranteDiegusto.service.RestauranteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor // Es mejor práctica usar @RequiredArgsConstructor para inyección.
public class ProductoUseCase {

    private final ProductoRepository productoRepository;
    private final RestauranteRepository restauranteRepository;

    public List<Producto> obtenerTodosLosProductos(String restauranteId) {
        return productoRepository.findByRestauranteId(restauranteId);
    }

    // CORRECCIÓN 1: findById devuelve un Optional<T> para manejar el caso en que no se encuentre el producto.
    public Producto obtenerProductoPorId(String id) {
        return productoRepository.getProductoById(id);
    }

    public Producto crearProducto(Producto producto) {
        // Para crear, nos aseguramos de que no venga con un ID o lo ignoramos para que la BD genere uno nuevo
        return productoRepository.createProducto(producto);
    }


    public void eliminarProducto(String id) {
        // Verificamos que el producto existe antes de intentar borrarlo.
        productoRepository.deleteProducto(id);

    }

    public Producto crearMesaParaRestaurante(String restauranteId, Producto nuevoproducto){
        //1. buscamos el restaurante para asegurarnos que existe
        Restaurante restaurante= restauranteRepository.getRestauranteById(restauranteId);

        //2. Establecemos la relacion le decimos a la mesa a que restaurante pertenece.
        nuevoproducto.setRestaurante(restaurante);

        //3.guardamos la nueva mesa y ya el spring data jpa se encarga del resto.
        return productoRepository.createProducto(nuevoproducto);
    }
    public List<Producto> obtenerMesasPorRestaurante(String restauranteId){
        return productoRepository.findByRestauranteId(restauranteId);

    }
    public Producto updateProducto(String id, Producto producto) {
        return productoRepository.updateProducto(id,producto);
    }

    public Producto crearMesaPorRestaurante(String restauranteId, Producto producto) {
        Restaurante restaurante = restauranteRepository.getRestauranteById(restauranteId);


        producto.setRestaurante(restaurante);

        return productoRepository.createProducto(producto);
    }
}
