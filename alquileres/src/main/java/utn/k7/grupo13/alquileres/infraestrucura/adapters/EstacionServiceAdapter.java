package utn.k7.grupo13.alquileres.infraestrucura.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.k7.grupo13.alquileres.domain.Estacion;
import utn.k7.grupo13.alquileres.service.EstacionService;

import java.util.List;
import java.util.Optional;
@Service
@Primary
public class EstacionServiceAdapter implements EstacionService {

    private RestTemplate template;


    @Autowired
    public EstacionServiceAdapter(RestTemplate template) {
        this.template = template;

    }


    @Override
    public Optional<Estacion> getEstacionById(Long id) {
        ResponseEntity<ApiResponse<Estacion>> res = template.exchange(
                "http://localhost:3001/api/estacion/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Estacion>>() {},
                id
        );

        if (res.getStatusCode().is2xxSuccessful()) {
            System.out.println("Respuesta exitosa: " + res.getBody().getData());
            return Optional.ofNullable(res.getBody().getData());
        } else {
            System.out.println("Error en la petición");
        }
        return Optional.empty();
    }


}
