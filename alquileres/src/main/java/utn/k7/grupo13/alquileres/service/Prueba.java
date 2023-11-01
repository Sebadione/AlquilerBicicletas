package utn.k7.grupo13.tpi.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Prueba {
    public static void main(String[] args) {
        LocalDateTime fecha1 = LocalDateTime.of(2023, 10, 16, 12, 0); // Fecha y hora inicial
        LocalDateTime fecha2 = LocalDateTime.of(2023, 10, 16, 15, 30); // Fecha y hora final

        // Calcular la diferencia entre las fechas
        Duration duracion = Duration.between(fecha1, fecha2);
        System.out.println(duracion.toHours());
    }
}
