package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Pedido;
import co.com.restaurantediegusto.RestauranteDiegusto.model.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPedidoProductoRespository extends JpaRepository<PedidoProducto,String> {
}
