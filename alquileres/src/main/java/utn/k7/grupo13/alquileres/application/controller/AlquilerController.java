package utn.k7.grupo13.alquileres.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.k7.grupo13.alquileres.application.ResponseHandler;
import utn.k7.grupo13.alquileres.application.request.AlquilerFiltroRequest;
import utn.k7.grupo13.alquileres.application.request.Moneda;
import utn.k7.grupo13.alquileres.application.request.PUTAlquilerRequest;
import utn.k7.grupo13.alquileres.application.request.PostAlquilerRequest;
import utn.k7.grupo13.alquileres.application.response.AlquilerResponse;
import utn.k7.grupo13.alquileres.domain.Alquiler;
import utn.k7.grupo13.alquileres.domain.Estacion;
import utn.k7.grupo13.alquileres.service.AlquilerService;
import utn.k7.grupo13.alquileres.service.EstacionService;
import utn.k7.grupo13.alquileres.service.MonedaService;


import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/api/alquiler")
public class AlquilerController {
    private AlquilerService alquilerService;
    private MonedaService monedaService;



    public AlquilerController(AlquilerService alquilerService, MonedaService monedaService) {
        this.alquilerService = alquilerService;
        this.monedaService = monedaService;
    }

    @PostMapping
    public ResponseEntity<Object> alquilarBicicleta(@RequestBody PostAlquilerRequest request) {
        Optional<Alquiler> alquiler = alquilerService.alquilarBicicleta(request.getIdEstacionRetiro(),
                request.getIdCliente());
        if (alquiler.isPresent()) {
            return ResponseHandler.created(new AlquilerResponse(
                    alquiler.get().getId(),
                    alquiler.get().getIdCliente(),
                    alquiler.get().getEstado(),
                    alquiler.get().getEstacionRetiro(),
                    null,
                    alquiler.get().getFechaHoraRetiro(),
                    alquiler.get().getFechaHoraDevolucion(),
                    null,
                    null
            ));

        } else {
            return ResponseHandler.badRequest("No se pudo crear el alquiler");
        }
    }

    @PutMapping()
    public ResponseEntity<Object> devolverBicicleta(@RequestBody(required = false) PUTAlquilerRequest request) {
        Optional<Alquiler> alquiler = alquilerService.devolverBicicleta(request.getIdEstacionDevolucion(), request.getIdAlquiler());
        if (request.getMoneda() == null) {
            request.setMoneda(Moneda.ARS);
        }

        double monto=monedaService.convertirMoneda(alquiler.get().getMonto(), request.getMoneda().getValor());

        monto = Math.round(monto * 100.0) / 100.0;

        if (monto == 0){
            return ResponseHandler.badRequest("No se pudo convertir la moneda");
        }

        if (alquiler.isPresent()) {
            return ResponseHandler.success(new AlquilerResponse(
                    alquiler.get().getId(),
                    alquiler.get().getIdCliente(),
                    alquiler.get().getEstado(),
                    alquiler.get().getEstacionRetiro(),
                    alquiler.get().getEstacionDevolucion(),
                    alquiler.get().getFechaHoraRetiro(),
                    alquiler.get().getFechaHoraDevolucion(),
                    monto + " " + request.getMoneda(),
                    alquiler.get().getIdTarifa().getId()
            ));

        } else {
            return ResponseHandler.badRequest("No se pudo devolver la bicicleta");
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAlquileresFiltro(@RequestBody(required = false) AlquilerFiltroRequest request ){
        List<AlquilerResponse> alquileresFiltro = alquilerService.getAlquilerConFiltro(request.getIdCliente(),
                request.getEstado(),
                request.getEstacionRetiro(),
                request.getEstacionDevolucion())
                .stream()
                .map(AlquilerResponse::from)
                .toList();
        if (!alquileresFiltro.isEmpty()){
            return ResponseHandler.success(alquileresFiltro);
        }else {
            return ResponseHandler.notFound("No existen alquileres que cumplan con los filtros ingresados.");
        }


    }





}
