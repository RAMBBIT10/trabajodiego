package co.com.restaurantediegusto.RestauranteDiegusto.usecase;

import co.com.restaurantediegusto.RestauranteDiegusto.model.*;
import co.com.restaurantediegusto.RestauranteDiegusto.service.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor


 public class PedidoUseCase {
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final MesaRepository mesaRepository;

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.getPedidos();
    }

    public Pedido obtenerPorId(String id) {
        return pedidoRepository.getPedidoById(id);
    }

    public Pedido crearPedido(Pedido pedidoRequest) {
        // 1. Validar y ocupar la mesa
        Mesa mesa = mesaRepository.getMesaById(pedidoRequest.getMesa().getId());

        if (!mesa.isDisponible()) {
            throw new IllegalStateException("La mesa " + mesa.getId() + " ya está ocupada.");
        }
        mesa.setDisponible(false);
        mesaRepository.createMesa(mesa);

        // 2. Crear la entidad Pedido principal que se va a guardar
        Pedido pedidoAGuardar = new Pedido();
        pedidoAGuardar.setMesa(mesa);
        pedidoAGuardar.setEstado(EstadoPedido.PENDIENTE);

        // 3. ¡AQUÍ ESTÁ LA LÓGICA CLAVE!
        // Transformar la lista de items temporales en entidades PedidoProducto reales
        List<PedidoProducto> itemsReales = new ArrayList<>();
        for (PedidoProducto itemTemporal : pedidoRequest.getItems()) {
            // Buscamos el producto completo en la BD para asegurarnos de que existe
            Producto producto = productoRepository.getProductoById(itemTemporal.getPedido().getId());

            // Creamos la entidad de unión que sí se va a guardar
            PedidoProducto itemReal = new PedidoProducto();
            itemReal.setPedido(pedidoAGuardar); // Enlaza con el pedido principal
            itemReal.setProducto(producto);     // Enlaza con el producto encontrado
            itemReal.setCantidad(itemTemporal.getCantidad()); // Establece la cantidad

            itemsReales.add(itemReal);
        }

        // 4. Asignar la lista de items reales y persistir
        pedidoAGuardar.setItems(itemsReales);

        // Al guardar el pedido, JPA guardará los items en cascada.
        return pedidoRepository.createPedido(pedidoAGuardar);
}

    public void eliminar(String id) {
        pedidoRepository.deletePedido(id);
    }
    public Pedido cerrarPedido(String pedidoId) {
        // 1. Obtener el pedido de la base de datos.
        Pedido pedido = pedidoRepository.getPedidoById(pedidoId);



        // 2. Calcular el subtotal. ¡AQUÍ ESTÁ LA NUEVA LÓGICA!
        // Iteramos sobre los items del pedido, multiplicamos el precio del producto por la cantidad de ese item, y sumamos todo.
        double subtotal = pedido.getItems().stream()
                .mapToDouble(item -> item.getProducto().getPrecio() * item.getCantidad())
                .sum();

        // 3. Aplicar descuento y establecer el total final.
        double descuentoAplicado = subtotal * (pedido.getDescuento() / 100.0);
        pedido.setTotal(subtotal - descuentoAplicado);

        // 4. Actualizar el estado del pedido.
        pedido.setEstado(EstadoPedido.CERRADO);

        // 5. Liberar la mesa asociada al pedido.
        Mesa mesa = pedido.getMesa();
        if (mesa != null) {
            mesa.setDisponible(true);
            mesaRepository.createMesa(mesa);
        }

        // 6. Guardar y devolver el pedido actualizado.
        return pedidoRepository.createPedido(pedido);
    }
public void cancelarPedido(String id) {
    Pedido pedido = obtenerPorId(id);

    // Liberar la mesa
    Mesa mesa = pedido.getMesa();
    mesa.setDisponible(true);
    mesaRepository.createMesa(mesa);

    // Marcar como cancelado
    pedido.setEstado(EstadoPedido.CANCELADO);

    pedidoRepository.createPedido(pedido); // Ya no se elimina
}
}

