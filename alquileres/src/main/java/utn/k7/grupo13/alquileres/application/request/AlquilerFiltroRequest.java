package utn.k7.grupo13.alquileres.application.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlquilerFiltroRequest {
    @Nullable
    private String idCliente;
    @Nullable
    private Integer estado;
    @Nullable
    private Long estacionRetiro;
    @Nullable
    private Long estacionDevolucion;


}
