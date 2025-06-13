package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository  {
    List<Mesa> findByDisponibleTrue();


    List<Mesa> findByRestauranteId(String restauranteId);

    List<Mesa> getMesas();


    Mesa getMesaById(String id);


    Mesa createMesa(Mesa mesa  );


    Mesa updateMesa(String id, Mesa Mesa);


    void deleteMesa(String id);
}





