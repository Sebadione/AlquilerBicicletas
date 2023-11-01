package utn.k7.grupo13.alquileres.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "estaciones")
@SequenceGenerator(name = "tu_secuencia_generator", sequenceName = "tu_secuencia", allocationSize = 19)
public class Estacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tu_secuencia_generator")

    private Long id;
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private double latitud;
    private double longitud;

    public Estacion(String nombre, double latitud, double longitud) {

        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fechaHoraCreacion = LocalDateTime.now();
    }
}
