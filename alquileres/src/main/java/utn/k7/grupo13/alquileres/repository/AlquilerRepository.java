package utn.k7.grupo13.alquileres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.k7.grupo13.alquileres.domain.Alquiler;


import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {


    List<Alquiler> findAllByEstado(int estado);
}
