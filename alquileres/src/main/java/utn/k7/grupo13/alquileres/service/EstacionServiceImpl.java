package utn.k7.grupo13.alquileres.service;

import org.springframework.stereotype.Service;

import utn.k7.grupo13.alquileres.domain.Estacion;
import utn.k7.grupo13.alquileres.repository.EstacionRepository;


import java.util.List;
import java.util.Optional;

@Service
public class EstacionServiceImpl implements EstacionService{
    private EstacionRepository estacionRepository;

    public EstacionServiceImpl(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    public Optional<List<Estacion>> getAllEstaciones() {
        System.out.println(estacionRepository.findAll());
        return Optional.of(estacionRepository.findAll());

    }

    @Override
    public Optional<Estacion>publicarEstacion(String nombre, double latitud, double longitud) {
        Estacion estacion = new Estacion(nombre, latitud, longitud);
        return Optional.of(estacionRepository.save(estacion));
    }

    @Override
    public Optional<Estacion> getEstacionById(Long id) {
        return Optional.of(estacionRepository.findById(id).get());
    }

    @Override
    public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        // Conversión de grados a metros (asumiendo que 1 grado = 110,000 metros)
        double LatitudMetros = Math.abs(lat1 - lat2) * 110;
        double LongitudMetros = Math.abs(lon1 - lon2) * 110;

        // Distancia euclídea
        return Math.sqrt(Math.pow(LatitudMetros, 2) + Math.pow(LongitudMetros, 2));



    }

    @Override
    public Optional<Estacion> getEstacionCercana(double latitud, double longitud) {
        //devolver optional empty si no se encuentra una estacion cercana
       return getAllEstaciones().get().stream()
                .min((estacion1, estacion2) -> {
                    double distancia1 = calcularDistancia(estacion1.getLatitud(), estacion1.getLongitud(), latitud, longitud);
                    double distancia2 = calcularDistancia(estacion2.getLatitud(), estacion2.getLongitud(), latitud, longitud);
                    return Double.compare(distancia1, distancia2);
                });


    }

}
