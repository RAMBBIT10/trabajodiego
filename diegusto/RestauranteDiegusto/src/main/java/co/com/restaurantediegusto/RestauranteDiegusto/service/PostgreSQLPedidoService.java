package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Pedido;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Producto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
@AllArgsConstructor
public class PostgreSQLPedidoService implements PedidoRepository{
    private SpringDataPedidoRespository repo;
    private PostgreSQLMesaService mesaService;


    @Override
    public List<Pedido> findByMesaId(String mesaId) {
        return mesaService.getMesaById(mesaId).getPedidos();
    }

    @Override
    public List<Pedido> getPedidos() {
        return repo.findAll();
    }

    @Override
    public Pedido getPedidoById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto not found with id " + id));
    }

    @Override
    public Pedido createPedido(Pedido pedido) {
        if (repo.existsById(pedido.getId())) {
            throw new IllegalArgumentException(
                    "A Pedido with the same ID already exists.");
        }
        return repo.save(pedido);
    }

    @Override
    public Pedido updatePedido(String id, Pedido pedido) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setEstado(pedido.getEstado());
                    existing.setTotal(pedido.getTotal());
                    existing.setItems(pedido.getItems());
                    return repo.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Pedido not found with id " + id));
    }

    @Override
    public void deletePedido(String id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Pedido con ID " + id + " no encontrado.");
        }

    }
}
