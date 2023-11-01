package utn.k7.grupo13.estaciones.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.k7.grupo13.estaciones.application.ResponseHandler;
import utn.k7.grupo13.estaciones.application.request.PostEstacionRequest;
import utn.k7.grupo13.estaciones.application.request.UbiRequest;
import utn.k7.grupo13.estaciones.application.response.EstacionesResponse;
import utn.k7.grupo13.estaciones.domain.Estacion;
import utn.k7.grupo13.estaciones.service.EstacionService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estacion")
public class EstacionController {


    private EstacionService estacionService;

    public EstacionController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }




    @GetMapping
    public ResponseEntity<Object> getAllEstaciones(@RequestBody(required = false) UbiRequest request) {

        if(request != null){
            Optional<Estacion> estacionCercana = estacionService.getEstacionCercana(request.getLatitud(), request.getLongitud());
            if (estacionCercana.isPresent()) {
                return ResponseHandler.success(
                        new EstacionesResponse(
                                estacionCercana.get().getId(),
                                estacionCercana.get().getNombre(),
                                estacionCercana.get().getFechaHoraCreacion(),
                                estacionCercana.get().getLatitud(),
                                estacionCercana.get().getLongitud()
                        ));
            } else {
                return ResponseHandler.notFound("No se encontraron estaciones");
            }
        }
        Optional<List<Estacion>> estaciones = estacionService.getAllEstaciones();
        if (estaciones.isPresent()) {
            return ResponseHandler.success(
                    estaciones.get().stream().map(estacion -> new EstacionesResponse(
                            estacion.getId(),
                            estacion.getNombre(),
                            estacion.getFechaHoraCreacion(),
                            estacion.getLatitud(),
                            estacion.getLongitud()

                    )).toList());
        } else {
            return ResponseHandler.notFound("No se encontraron estaciones");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEstacionById(@PathVariable Long id) {
        Optional<Estacion> estacion = estacionService.getEstacionById(id);
        if (estacion.isPresent()) {
            return ResponseHandler.success(
                    new EstacionesResponse(
                            estacion.get().getId(),
                            estacion.get().getNombre(),
                            estacion.get().getFechaHoraCreacion(),
                            estacion.get().getLatitud(),
                            estacion.get().getLongitud()
                    ));
        } else {
            return ResponseHandler.notFound("No se encontro la estacion");
        }
    }
    @PostMapping
    public ResponseEntity<Object> publicarEstacion(@RequestBody PostEstacionRequest estacion) {
        Optional<Estacion> estacionPublicada = estacionService.publicarEstacion(estacion.getNombre(), estacion.getLatitud(), estacion.getLongitud());
        if (estacionPublicada.isPresent()) {
            return ResponseHandler.created(new EstacionesResponse(
                    estacionPublicada.get().getId(),
                    estacionPublicada.get().getNombre(),
                    estacionPublicada.get().getFechaHoraCreacion(),
                    estacionPublicada.get().getLatitud(),
                    estacionPublicada.get().getLongitud()
            ));
        } else {
            return ResponseHandler.badRequest("No se pudo publicar la estacion");
        }
    }

}
