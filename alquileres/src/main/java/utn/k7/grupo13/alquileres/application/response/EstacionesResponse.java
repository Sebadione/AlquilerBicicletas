package utn.k7.grupo13.alquileres.application.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

public class EstacionesResponse {
    private Long id;
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private double latitud;
    private double longitud;

    public EstacionesResponse(Long id, String nombre, LocalDateTime fechaHoraCreacion, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
