package co.com.restaurantediegusto.RestauranteDiegusto.controller;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Mesa;
import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import co.com.restaurantediegusto.RestauranteDiegusto.usecase.MesaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/restaurantes/{restauranteId}/mesas")
@RequiredArgsConstructor
public class MesaController {
    private final MesaUseCase mesaUseCase;

    @PostMapping
    public ResponseEntity<Mesa> crearMesa(@PathVariable String restauranteId, @RequestBody Mesa mesa){

        Mesa mesas = mesaUseCase.crearMesaPorRestaurante(restauranteId, mesa);
        return ResponseEntity.status(201).build();
    }
    @GetMapping
    public ResponseEntity<List<Mesa>> obtenerMesas(@PathVariable String restauranteId){
        List<Mesa> mesas = mesaUseCase.obtenerMesasPorRestaurante(restauranteId);
        return ResponseEntity.ok(mesas);
    }

}
