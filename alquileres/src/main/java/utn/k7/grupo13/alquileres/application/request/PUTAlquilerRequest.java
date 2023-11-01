package utn.k7.grupo13.alquileres.application.request;

import lombok.Data;

@Data
public class PUTAlquilerRequest {
    private Moneda moneda;

    public PUTAlquilerRequest(Moneda moneda) {
        this.moneda = moneda;
    }
}
