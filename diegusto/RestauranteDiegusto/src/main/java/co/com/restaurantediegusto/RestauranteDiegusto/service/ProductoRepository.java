package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Mesa;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductoRepository  {

    List<Producto> findByRestauranteId(String restauranteId);

    List<Producto> getProducto();


    Producto getProductoById(String id);


    Producto createProducto(Producto producto  );


    Producto updateProducto(String id, Producto producto);


    void deleteProducto(String id);

}
