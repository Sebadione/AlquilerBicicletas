package utn.k7.grupo13.alquileres.application.request;

import jakarta.annotation.Nullable;

import lombok.Data;

@Data
public class PUTAlquilerRequest {

    private Long idAlquiler;
    private Long idEstacionDevolucion;
    @Nullable
    private Moneda moneda;


}
