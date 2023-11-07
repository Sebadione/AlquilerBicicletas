package utn.k7.grupo13.alquileres.application.request;

import lombok.Data;

@Data
public class PostAlquilerRequest {
    private Long idEstacionRetiro;
    private String idCliente;
}
