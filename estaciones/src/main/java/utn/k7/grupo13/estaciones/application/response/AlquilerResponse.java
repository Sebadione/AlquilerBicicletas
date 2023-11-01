package utn.k7.grupo13.estaciones.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor

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
}
