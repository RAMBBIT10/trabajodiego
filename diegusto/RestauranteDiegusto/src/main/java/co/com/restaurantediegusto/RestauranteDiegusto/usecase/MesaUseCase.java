package co.com.restaurantediegusto.RestauranteDiegusto.usecase;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Mesa;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import co.com.restaurantediegusto.RestauranteDiegusto.service.MesaRepository;
import co.com.restaurantediegusto.RestauranteDiegusto.service.RestauranteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component

@AllArgsConstructor
public class MesaUseCase {

    private final MesaRepository mesaRepository;
    private final RestauranteRepository restauranteRepository; //necesitamos esto para encontrar el restaurante


    public Mesa crearMesaParaRestaurante(String restauranteId, Mesa nuevaMesa){
        //1. buscamos el restaurante para asegurarnos que existe
        Restaurante restaurante= restauranteRepository.getRestauranteById(restauranteId);
        //2. Establecemos la relacion le decimos a la mesa a que restaurante pertenece.
        nuevaMesa.setRestaurante(restaurante);

        //3.guardamos la nueva mesa y ya el spring data jpa se encarga del resto.
        return mesaRepository.createMesa(nuevaMesa);
    }
    public List<Mesa> obtenerMesasPorRestaurante(String restauranteId){
        return mesaRepository.findByRestauranteId(restauranteId);

    }

    public Mesa crearMesaPorRestaurante(String restauranteId, Mesa mesa) {
        Restaurante restaurante = restauranteRepository.getRestauranteById(restauranteId);


        mesa.setRestaurante(restaurante);

        return mesaRepository.createMesa(mesa);
    }
}
