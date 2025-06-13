package co.com.restaurantediegusto.RestauranteDiegusto.service;


import co.com.restaurantediegusto.RestauranteDiegusto.model.Pedido;
import co.com.restaurantediegusto.RestauranteDiegusto.model.PedidoProducto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoProductoRepository  {
    List<PedidoProducto> findByPedidoId(String pedidoId);

    List<PedidoProducto> getPedidosProductos();


    PedidoProducto getPedidoProductoById(String id);


    PedidoProducto createPedidoProducto(PedidoProducto pedidoProducto  );


    PedidoProducto updatePedidoProducto (String id, PedidoProducto pedidoProducto);


    void deletePedidoProducto(String id);

}
