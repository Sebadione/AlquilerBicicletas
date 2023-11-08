package utn.k7.grupo13.alquileres.service;

import utn.k7.grupo13.alquileres.domain.Alquiler;
import utn.k7.grupo13.alquileres.domain.Estacion;


import java.util.List;
import java.util.Optional;

public interface AlquilerService {
    public Optional<Alquiler> alquilarBicicleta(Long idEstacion, String idCliente);
    public Optional<Alquiler> devolverBicicleta(Long idEstacion, Long idAlquiler);
    public Optional<List<Alquiler>> getAlquileresEstacionEnCurso();
    public Estacion invocarServicio(Long id);

    public Optional<List<Alquiler>> getAlquilerConFiltro(String idCliente, Integer estado, Long estacionRetiro,
                                                   Long estacionDevolucion);



}
