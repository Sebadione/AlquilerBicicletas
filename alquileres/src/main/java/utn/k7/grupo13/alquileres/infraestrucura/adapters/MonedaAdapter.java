package utn.k7.grupo13.alquileres.infraestrucura.adapters;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.k7.grupo13.alquileres.service.MonedaService;
import java.util.Objects;

@Service
public class MonedaAdapter implements MonedaService {

    private RestTemplate template;

    public MonedaAdapter(RestTemplate template) {
        this.template = template;
    }

    @Override
    public double convertirMoneda(double monto, String monedaDestino) {
        ResponseEntity<MonedaResponse> res = template.postForEntity(
                "http://34.82.105.125:8080/convertir",
                new MonedaRequest(monedaDestino,monto),
                MonedaResponse.class
        );
        if (res.getStatusCode().is2xxSuccessful()) {
            System.out.println("La moneda es: " + Objects.requireNonNull(res.getBody()).getImporte());
            return Objects.requireNonNull(res.getBody()).getImporte();
        }
        return 0;

    }
}
