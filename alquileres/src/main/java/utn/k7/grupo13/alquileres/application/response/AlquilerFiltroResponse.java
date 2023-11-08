package utn.k7.grupo13.alquileres.application.response;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AlquilerFiltroResponse {
    @Nullable
    private String idCliente;
    @Nullable
    private Integer estado;
    @Nullable
    private Long estacionRetiro;
    @Nullable
    private Long estacionDevolucion;


}
