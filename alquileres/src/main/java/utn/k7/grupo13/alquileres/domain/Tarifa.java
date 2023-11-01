package utn.k7.grupo13.alquileres.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tarifas")
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;
    private int tipoTarifa;
    private String definicion;
    private int diaSemana;
    private Integer diaMes;
    private Integer mes;
    private Integer anio;
    private double montoFijoAlquiler;
    private double montoMinutoFraccion;
    private double montoKm;
    private double montoHora;



}
