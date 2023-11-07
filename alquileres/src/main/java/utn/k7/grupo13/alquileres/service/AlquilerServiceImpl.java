package utn.k7.grupo13.alquileres.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import utn.k7.grupo13.alquileres.domain.Alquiler;
import utn.k7.grupo13.alquileres.domain.Estacion;
import utn.k7.grupo13.alquileres.domain.EstadoAlquiler;
import utn.k7.grupo13.alquileres.domain.Tarifa;
import utn.k7.grupo13.alquileres.repository.AlquilerRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private AlquilerRepository alquilerRepository;
    private EstacionService estacionService;

    private TarifaService tarifaService;
    public AlquilerServiceImpl(AlquilerRepository alquilerRepository, EstacionService estacionService,TarifaService tarifaService){
        this.alquilerRepository = alquilerRepository;
        this.estacionService = estacionService;
        this.tarifaService = tarifaService;
    }


    @Override
    public Optional<Alquiler> alquilarBicicleta(Long idEstacionRetiro, String idCliente) {

        Alquiler alquiler = new Alquiler(idCliente,
                EstadoAlquiler.INICIADO.getValor(),
                estacionService.getEstacionById(idEstacionRetiro).get(),
                LocalDateTime.now(), null,
                0,
                null);
        return Optional.of(alquilerRepository.save(alquiler));

    }

    @Override
    public Optional<Alquiler> devolverBicicleta(Long idEstacion, Long idAlquiler) {
        Tarifa tarifa = tarifaService.getTarifa();
        Alquiler alquiler = alquilerRepository.findById(idAlquiler).get();
        alquiler.setEstacionDevolucion(estacionService.getEstacionById(idEstacion).get());
        alquiler.setFechaHoraDevolucion(LocalDateTime.now());
        alquiler.setEstado(EstadoAlquiler.FINALIZADO.getValor());
        alquiler.setMonto(tarifaService.calcularTarifa(alquiler,tarifa));
        alquiler.setIdTarifa(tarifa);
        return Optional.of(alquilerRepository.save(alquiler));
    }

    @Override
    public Optional<List<Alquiler>> getAlquileresEstacionEnCurso() {
        return Optional.of(alquilerRepository.findAllByEstado(EstadoAlquiler.INICIADO.getValor()));
    }




    @Override
    public Estacion invocarServicio(Long id) {
        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<Estacion> res = template.getForEntity(
                    "http://localhost:3001/api/estacion/{id}", Estacion.class, id
            );

            if (res.getStatusCode().is2xxSuccessful()) {
                System.out.println("Respuesta exitosa: " + res.getBody());
                return res.getBody();
            } else {
                System.out.println("Error en la petición");
            }
        } catch (HttpClientErrorException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return null;
    }



}

