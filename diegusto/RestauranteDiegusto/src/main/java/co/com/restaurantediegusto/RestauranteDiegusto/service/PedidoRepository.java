package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PedidoRepository {

    List<Pedido> findByMesaId(String mesaId);

    List<Pedido> getPedidos();


    Pedido getPedidoById(String id);


    Pedido createPedido(Pedido pedido  );


    Pedido updatePedido(String id, Pedido pedido);


    void deletePedido(String id);

}