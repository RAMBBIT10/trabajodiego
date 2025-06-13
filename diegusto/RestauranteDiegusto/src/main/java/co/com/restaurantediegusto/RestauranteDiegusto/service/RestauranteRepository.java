package co.com.restaurantediegusto.RestauranteDiegusto.service;

import co.com.restaurantediegusto.RestauranteDiegusto.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface RestauranteRepository {
    List<Restaurante> getRestaurantes();


    Restaurante getRestauranteById(String id);


    Restaurante createRestaurante(Restaurante restaurante);



    Restaurante updateRestaurante(String id, Restaurante restaurante);


    void deleteRestaurante(String id);


    // Aquì se implementan metodos de CRUD para usuarios
    //Normalmente son get (Read), create, update, delete
    //Puedes crear metodos personalizados que luego tendàs que implementar en su totlaidad



}
