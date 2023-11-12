package utn.k7.grupo13.alquileres.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import utn.k7.grupo13.alquileres.domain.Alquiler;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder

public class AlquilerResponse {
    private Long id;
    private String idCliente;
    private int estado;


    private Long estacionRetiro;

    private Long estacionDevolucion;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private String monto;
    private Long idTarifa;

    public static AlquilerResponse from (Alquiler alquiler){
        return AlquilerResponse.builder()
                .id(alquiler.getId())
                .idCliente(alquiler.getIdCliente())
                .estado(alquiler.getEstado())
                .estacionRetiro(alquiler.getEstacionRetiro())
                .estacionDevolucion(alquiler.getEstacionDevolucion())
                .fechaHoraRetiro(alquiler.getFechaHoraRetiro())
                .fechaHoraDevolucion(alquiler.getFechaHoraDevolucion())
                .monto((String.valueOf(alquiler.getMonto()) ))

                .build();

    }
}
