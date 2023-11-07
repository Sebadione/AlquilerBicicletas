package utn.k7.grupo13.alquileres.infraestrucura.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AdapterConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
