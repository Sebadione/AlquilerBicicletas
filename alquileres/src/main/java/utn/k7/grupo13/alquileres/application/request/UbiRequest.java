package utn.k7.grupo13.alquileres.application.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UbiRequest {

    private double latitud;
    private double longitud;
}
