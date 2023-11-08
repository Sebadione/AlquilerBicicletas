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
        if (estacionService.getEstacionById(idEstacionRetiro).isEmpty()) {
            return Optional.empty();
        }
        Alquiler alquiler = new Alquiler(idCliente,
                EstadoAlquiler.INICIADO.getValor(),
                idEstacionRetiro,
                LocalDateTime.now(), null,
                0,
                null);
        return Optional.of(alquilerRepository.save(alquiler));

    }

    @Override
    public Optional<Alquiler> devolverBicicleta(Long idEstacion, Long idAlquiler) {
        if (estacionService.getEstacionById(idEstacion).isEmpty()) {
            return Optional.empty();
        }
        Tarifa tarifa = tarifaService.getTarifa();
        Alquiler alquiler = alquilerRepository.findById(idAlquiler).get();
        alquiler.setEstacionDevolucion(idEstacion);
        alquiler.setFechaHoraDevolucion(LocalDateTime.now());
        alquiler.setEstado(EstadoAlquiler.FINALIZADO.getValor());
        alquiler.setMonto(tarifaService.calcularTarifa(alquiler,tarifa));
        alquiler.setIdTarifa(tarifa);
        return Optional.of(alquilerRepository.save(alquiler));
    }

    @Override
    public List<Alquiler> getAlquilerConFiltro(String idCliente, Integer estado, Long estacionRetiro,
                                                         Long estacionDevolucion) {
        List<Alquiler> lista = alquilerRepository.findAll();
        if (idCliente != null){
            lista = lista.stream().filter(x -> x.getIdCliente().equals(idCliente)).toList();
        }
        if (estado != null){
            lista = lista.stream().filter(x -> x.getEstado() == estado).toList();
        }
        if (estacionRetiro != null){
            lista = lista.stream().filter(x -> x.getEstacionRetiro().equals(estacionDevolucion)).toList();
        }
        if (estacionDevolucion != null){
            lista = lista.stream().filter(x -> x.getEstacionRetiro().equals(estacionRetiro)).toList();
        }
        return lista;
    }


}

