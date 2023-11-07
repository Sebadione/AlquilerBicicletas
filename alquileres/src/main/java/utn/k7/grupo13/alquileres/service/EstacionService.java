package utn.k7.grupo13.alquileres.service;

import utn.k7.grupo13.alquileres.domain.Estacion;


import java.util.List;
import java.util.Optional;

public interface EstacionService {


    public Optional<Estacion> getEstacionById(Long id);


}
