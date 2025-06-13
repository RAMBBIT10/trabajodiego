package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.PedidoProducto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
@AllArgsConstructor
public class PostgreSQLPedidoProductoService implements PedidoProductoRepository{
    private SpringDataPedidoProductoRespository repo;
    private PostgreSQLPedidoService pedidoService;
    private PostgreSQLProductoService productoService;

    @Override
    public List<PedidoProducto> findByPedidoId(String pedidoId) {
        return pedidoService.getPedidoById(pedidoId).getItems();
    }

    @Override
    public List<PedidoProducto> getPedidosProductos() {
        return repo.findAll();
    }

    @Override
    public PedidoProducto getPedidoProductoById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("PedidoProducto not found with id " + id));
    }

    @Override
    public PedidoProducto createPedidoProducto(PedidoProducto pedidoProducto) {
        if (repo.existsById(pedidoProducto.getId())) {
            throw new IllegalArgumentException(
                    "A PedidoProducto with the same ID already exists.");
        }
        return repo.save(pedidoProducto);
    }

    @Override
    public PedidoProducto updatePedidoProducto(String id, PedidoProducto pedidoProducto) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setPedido(pedidoProducto.getPedido());
                    existing.setProducto(pedidoProducto.getProducto());
                    existing.setPrecio(pedidoProducto.getPrecio());
                    existing.setCantidad(pedidoProducto.getCantidad());
                    return repo.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("PedidoProducto not found with id " + id));
    }

    @Override
    public void deletePedidoProducto(String id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("PedidoProducto con ID " + id + " no encontrado.");

    }
}
}