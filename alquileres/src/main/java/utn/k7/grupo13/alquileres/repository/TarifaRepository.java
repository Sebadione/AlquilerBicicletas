package utn.k7.grupo13.alquileres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.k7.grupo13.alquileres.domain.Tarifa;


import java.util.Optional;

public interface TarifaRepository extends JpaRepository<Tarifa, Long>{

    Tarifa getTarifaByDiaSemana(int diaSemana);
    Optional<Tarifa> getTarifaByDiaMesAndMesAndAnio(int diaMes, int mes, int anio);
}
