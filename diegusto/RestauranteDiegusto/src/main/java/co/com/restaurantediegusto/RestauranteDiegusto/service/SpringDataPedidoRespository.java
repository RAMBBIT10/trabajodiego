package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Pedido;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPedidoRespository extends JpaRepository<Pedido,String> {
}
