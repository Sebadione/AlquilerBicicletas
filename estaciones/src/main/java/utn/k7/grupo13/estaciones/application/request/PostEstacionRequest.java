package utn.k7.grupo13.estaciones.application.request;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PostEstacionRequest {
    private String nombre;
    private double latitud;
    private double longitud;

}
