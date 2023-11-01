package utn.k7.grupo13.alquileres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.k7.grupo13.alquileres.domain.Estacion;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion,Long> {
}
